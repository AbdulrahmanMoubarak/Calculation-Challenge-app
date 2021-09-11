package com.training.calculationchallengev2.ui.boarding.vpscreens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.training.calculationchallengev2.R
import kotlinx.android.synthetic.main.fragment_screen2.view.*

class FragmentScreen2 : Fragment() {

    private val TAG = "FragmentScreen2"
    private lateinit var con: ViewGroup
    companion object{
        private var Oflag = false
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_screen2, container, false)
        view.screen2finish.setOnClickListener {
            SaveSharedPreference()
            findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment)
        }

        return view
    }

    private fun SaveSharedPreference(){
        var sp = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        var editor = sp.edit()
        editor.putBoolean("boarding done", true).apply()
    }


}