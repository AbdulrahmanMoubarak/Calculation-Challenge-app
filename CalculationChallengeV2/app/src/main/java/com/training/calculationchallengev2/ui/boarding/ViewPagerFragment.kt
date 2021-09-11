package com.training.calculationchallengev2.ui.boarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.training.calculationchallengev2.R
import com.training.calculationchallengev2.ui.boarding.vpscreens.FragmentScreen1
import com.training.calculationchallengev2.ui.boarding.vpscreens.FragmentScreen2
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class ViewPagerFragment : Fragment() {
    private lateinit var adapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_pager, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FragmentScreen1(),
            FragmentScreen2()
        )

        adapter = ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)
        view.general_viewpager.adapter  = adapter

        return view
    }
}