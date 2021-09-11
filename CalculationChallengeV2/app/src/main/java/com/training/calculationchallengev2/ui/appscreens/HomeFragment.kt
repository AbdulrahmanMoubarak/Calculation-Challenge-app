package com.training.calculationchallengev2.ui.appscreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.training.calculationchallengev2.R
import com.training.calculationchallengev2.util.Lang
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if("en".equals(Lang.checkLang(this))){
            this.activity?.let { Lang.setLocale(this, it, "en") }
        }
        else if("ar".equals(Lang.checkLang(this))){
            this.activity?.let { Lang.setLocale(this, it, "ar") }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.ButtonStart.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_quizFragment)
        }

        view.ButtonTop.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recordsFragment)
        }

        view.ButtonSettings.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
        }
        view.ButtonExit.setOnClickListener {
            activity?.finish()
        }

        return view
    }



}