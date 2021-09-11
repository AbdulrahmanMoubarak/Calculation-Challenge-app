package com.training.calculationchallengev2.ui.appscreens

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.training.calculationchallengev2.R

class SplashFragment : Fragment(R.layout.fragment_splash) {
    // TODO: Rename and change types of parameters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Handler(Looper.getMainLooper())
            .postDelayed({
                if(checkBoarding()) {
                    findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                }
                else{
                    findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment2)
                }
        }, 2000)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    private fun checkBoarding(): Boolean{
        var sp = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return  sp.getBoolean("boarding done", false)
    }




}