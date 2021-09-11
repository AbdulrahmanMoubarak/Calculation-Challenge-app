package com.training.calculationchallengev2.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.training.calculationchallengev2.R
import com.training.calculationchallengev2.model.UserModel
import kotlinx.android.synthetic.main.adapter_list_view.view.*

class UsreListAdapter(context: Context, list: ArrayList<UserModel>):
    ArrayAdapter<UserModel> (context, 0, list){

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var name = getItem(position)?.name
        var score = getItem(position)?.score

        if (convertView == null) {
            val view2 = LayoutInflater.from(getContext()).inflate(R.layout.adapter_list_view, parent, false);
            view2.nametxt?.setText(name)
            view2.scoretxt?.setText(score)

            return view2
        }
        else {

            convertView?.nametxt?.setText(name)
            convertView?.scoretxt?.setText(score)

            return convertView
        }
    }

}