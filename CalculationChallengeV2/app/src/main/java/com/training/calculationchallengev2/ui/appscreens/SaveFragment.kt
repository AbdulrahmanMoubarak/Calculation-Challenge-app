package com.training.calculationchallengev2.ui.appscreens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.training.calculationchallengev2.R
import com.training.calculationchallengev2.ui.main.DatabaseViewModel
import kotlinx.android.synthetic.main.fragment_save.view.*
import com.google.android.material.snackbar.Snackbar
import com.training.calculationchallengev2.util.RestoreQuiz
import java.util.concurrent.Executors


class SaveFragment : Fragment() {

    private lateinit var dbvm: DatabaseViewModel
    private var thread = Executors.newFixedThreadPool(1)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_save, container, false)

        if(savedInstanceState != null)
            view.name_editText.setText(savedInstanceState.getString(RestoreQuiz.USER_INPUT))

        view?.button_goBack?.setOnClickListener {
            findNavController().navigate(R.id.action_saveFragment_to_homeFragment)
        }

        view?.button_save?.setOnClickListener {
            thread.execute {
                dbvm = DatabaseViewModel(requireContext())
                var isSuccessfull =
                    dbvm.insertData(view.name_editText.text.toString(), loadLastScore())
                var snackText =
                    if (isSuccessfull) getString(R.string.save_success) else getString(R.string.save_fail)
                Snackbar.make(view, snackText, Snackbar.LENGTH_LONG)
                    .show()
            }
            findNavController().navigate(R.id.action_saveFragment_to_homeFragment)
        }
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(RestoreQuiz.USER_INPUT, view?.name_editText?.text.toString())
        super.onSaveInstanceState(outState)
    }

    private fun loadLastScore(): String{
        var sp = requireActivity().getSharedPreferences("save_score", Context.MODE_PRIVATE)
        return sp.getString("score", "0").toString()
    }
}