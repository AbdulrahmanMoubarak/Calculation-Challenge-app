package com.training.calculationchallengev2.ui.appscreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.training.calculationchallengev2.R
import com.training.calculationchallengev2.ui.main.DatabaseViewModel
import com.training.calculationchallengev2.ui.main.UsreListAdapter
import kotlinx.android.synthetic.main.fragment_records.view.*


class RecordsFragment : Fragment() {
    private lateinit var viewModel: DatabaseViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = DatabaseViewModel(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_records, container, false)

        var users = viewModel.getLatestData()

        if (users.size == 0){
            view.no_display.visibility = View.VISIBLE
        }

        val adapter = UsreListAdapter(requireContext(), users)
        view.datalistview.adapter = adapter

        return view
    }
}