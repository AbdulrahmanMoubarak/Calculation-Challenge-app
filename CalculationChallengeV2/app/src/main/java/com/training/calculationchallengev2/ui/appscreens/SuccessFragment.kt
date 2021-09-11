package com.training.calculationchallengev2.ui.appscreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.training.calculationchallengev2.R
import kotlinx.android.synthetic.main.fragment_fail.view.*
import kotlinx.android.synthetic.main.fragment_fail.view.button_continue
import kotlinx.android.synthetic.main.fragment_success.view.*


class SuccessFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_success, container, false)
        view?.button_continue2?.setOnClickListener {
            findNavController().navigate(R.id.action_successFragment_to_homeFragment)
        }

        view?.img_save?.setOnClickListener{

            findNavController().navigate(R.id.action_successFragment_to_saveFragment)
        }
        return view
    }
}