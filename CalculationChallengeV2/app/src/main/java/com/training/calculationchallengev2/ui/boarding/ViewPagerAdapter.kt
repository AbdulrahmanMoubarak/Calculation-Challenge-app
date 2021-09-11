package com.training.calculationchallengev2.ui.boarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    list:ArrayList<Fragment>,
    fm: FragmentManager,
    lifecycle: Lifecycle): FragmentStateAdapter(fm, lifecycle) {

    val frag_list: ArrayList<Fragment> = list

    override fun getItemCount(): Int {
        return frag_list.size
    }

    override fun createFragment(position: Int): Fragment {
        return frag_list[position]
    }

}