package com.training.calculationchallengev2.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import androidx.lifecycle.ViewModel
import com.training.calculationchallengev2.model.DatabaseModel
import com.training.calculationchallengev2.model.UserModel

class DatabaseViewModel(context: Context) : ViewModel() {
    private val TAG = "DataBaseViewModel"


    private val dbModel = DatabaseModel(context)

     fun insertData(name: String, score: String): Boolean{
        return dbModel.insertData(name, score)
    }

    @SuppressLint("Range")
     fun getLatestData(): ArrayList<UserModel> {
        var cursor: Cursor = dbModel.retrieveData()
        val list: ArrayList<UserModel> = ArrayList<UserModel>()
        if(cursor.count != 0){
            if(cursor.moveToFirst()){
                do{
                    list.add(UserModel(cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("score"))))
                }while (cursor.moveToNext())
            }
        }
        return list
    }

    fun deletetData(){
        dbModel.deleteData()
    }
}