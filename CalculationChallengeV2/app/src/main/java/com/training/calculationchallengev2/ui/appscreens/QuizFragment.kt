package com.training.calculationchallengev2.ui.appscreens

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.*
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.fragment.findNavController
import com.training.calculationchallengev2.R
import com.training.calculationchallengev2.model.Equation
import com.training.calculationchallengev2.util.EquationGenerator
import com.training.calculationchallengev2.util.Lang
import com.training.calculationchallengev2.util.RestoreQuiz
import kotlinx.android.synthetic.main.fragment_quiz.view.*


class QuizFragment : Fragment() {
    private val TAG = "QuizFragment"

    private var Start_timer: CountDownTimer? = null
    private var globalTimer: CountDownTimer? = null
    private var firstTimerCount: Int = -1
    private var secondTimerCount: Int = -1
    private var start_timer_finished = false
    private var equation : Equation? = null

    companion object {
        val CHANNEL_ID = "quiz_channel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if("en".equals(Lang.checkLang(this))){
            this.activity?.let { Lang.setLocale(this, it, "en") }
        }
        else if("ar".equals(Lang.checkLang(this))){
            this.activity?.let { Lang.setLocale(this, it, "ar") }
        }

        createNotificationChannel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_quiz, container, false)
        if(savedInstanceState != null) {
            start_timer_finished = savedInstanceState?.getBoolean(RestoreQuiz.FIRST_TIMER_FINISHED) as Boolean
            if (!start_timer_finished) {
                //Log.d(TAG, "onSaveInstanceState: first timer not finished yet")
                firstTimerCount = savedInstanceState.getInt(RestoreQuiz.FIRST_TIMER_COUNT)
                Log.d(TAG, "onSaveInstanceState: first timer not finished yet time = $firstTimerCount")

            } else {
                equation = savedInstanceState.getSerializable(RestoreQuiz.EQUATION) as Equation
                secondTimerCount = savedInstanceState.getInt(RestoreQuiz.SECOND_TIMER_COUNT)
                view.editText.setText(savedInstanceState.getString(RestoreQuiz.USER_INPUT, ""))
            }
        }

        view.ButtonSol.setOnClickListener {

            globalTimer?.cancel()

            if(equation?.solution.equals(view.editText.text.toString())){
                saveLastScore()
                findNavController().navigate(R.id.action_quizFragment_to_successFragment)
            }
            else
            {
                findNavController().navigate(R.id.action_quizFragment_to_failFragment)
            }
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        getStarted()
    }

    override fun onDestroy() {
        super.onDestroy()
        Start_timer?.cancel()
        globalTimer?.cancel()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(RestoreQuiz.FIRST_TIMER_FINISHED, start_timer_finished)
        if(!start_timer_finished) {
            outState.putInt(RestoreQuiz.FIRST_TIMER_COUNT, firstTimerCount)
        }
        else {
            outState.putInt(RestoreQuiz.SECOND_TIMER_COUNT, secondTimerCount)
            outState.putString(RestoreQuiz.USER_INPUT, view?.editText?.text.toString())
            outState.putSerializable(RestoreQuiz.EQUATION, equation)
        }
        Log.d(TAG, "onSaveInstanceState: Saved successfully")
        super.onSaveInstanceState(outState)
    }

    private fun getStarted(){
        if(start_timer_finished){
            runGame()
        }
        else{
            countDown3(firstTimerCount)
        }
    }

    private fun countDown3(time: Int){
        var actualTime = time
        Log.d(TAG, "countDown3: $time")
        if(actualTime == -1){
            actualTime = 4
        }
        Log.d(TAG, "countDown3: actual time = $actualTime")
        Start_timer = object: CountDownTimer(actualTime.toLong()*1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val t = millisUntilFinished/1000
                firstTimerCount = t.toInt()
                if (t.toInt() != 0)
                    view?.counter?.setText(t.toString())
                else
                    view?.counter?.setText(getString(R.string.startsol))
            }
            override fun onFinish() {
                start_timer_finished = true
                runGame()
            }
        }
        Start_timer?.start()
    }


    private fun setItemsVisible(){
        view?.counter?.visibility = View.GONE
        view?.txt_clock?.visibility = View.VISIBLE
        view?.equationtxt?.visibility = View.VISIBLE
        view?.solve?.visibility = View.VISIBLE
        view?.ButtonSol?.visibility = View.VISIBLE
        view?.editText?.visibility = View.VISIBLE
    }

    private fun setUi(eq: Equation){
        view?.txt_clock?.text = ("00:${eq.time.toString()}")
        view?.equationtxt?.text = eq.formula
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Custom channel 1",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = getString(R.string.channel_description)
            }

            val notifManager = requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notifManager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(context: Context){
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_calculator_icon)
            .setContentTitle(getString(R.string.time))
            .setContentText(getString(R.string.unfortunately))
            .setStyle(NotificationCompat.BigTextStyle().bigText(getString(R.string.notification)))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setCategory(NotificationCompat.CATEGORY_EVENT)

        with(NotificationManagerCompat.from(requireContext())) {
            notify(0, builder.build())
        }
    }

    private fun runGame(){
        if(equation == null) {
            equation = EquationGenerator().generateRandomEquation()
        }
        setItemsVisible()
        setUi(equation as Equation)
        if(secondTimerCount != -1)
            prepareTimer(secondTimerCount)
        else
            prepareTimer(equation?.time as Int)

        globalTimer?.start()
    }

    private fun prepareTimer(time: Int){
        globalTimer = object: CountDownTimer(time.toLong()*1000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                val time_remaining = millisUntilFinished/1000
                secondTimerCount = time_remaining.toInt()
                view?.txt_clock?.setText("00:${time_remaining.toString()}")
                Log.d(TAG, "onTick: ${time_remaining}")
            }
            override fun onFinish() {
                createNotification(requireContext())
                findNavController().navigate(R.id.action_quizFragment_to_failFragment)
            }
        }
    }

    private fun saveLastScore(){
        val sp = requireActivity().getSharedPreferences("save_score", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("score", equation?.score.toString()).apply()
    }
}