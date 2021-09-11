package com.training.calculationchallengev2.ui.boarding.vpscreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.training.calculationchallengev2.R
import kotlinx.android.synthetic.main.fragment_screen1.view.*

class FragmentScreen1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_screen1, container, false)

        val viewpager = activity?.findViewById<ViewPager2>(R.id.general_viewpager)

        view.screen1next.setOnClickListener {
            viewpager?.currentItem = 1
        }

        return view
    }
}