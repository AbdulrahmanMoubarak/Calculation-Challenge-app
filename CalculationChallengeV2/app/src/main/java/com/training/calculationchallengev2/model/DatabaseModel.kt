package com.training.calculationchallengev2.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.training.calculationchallengev2.util.DatabaseConstants

class DatabaseModel(context: Context):
    SQLiteOpenHelper(context,DatabaseConstants.DATABASE_NAME,null,DatabaseConstants.DATABASE_VERSION){


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE ${DatabaseConstants.TABLE_NAME}(${DatabaseConstants.NAME_COL} TEXT PRIMARY KEY, ${DatabaseConstants.SCORE_COL} TEXT) ")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${DatabaseConstants.TABLE_NAME}" )
    }

    fun insertData(name: String, score:String):Boolean{
        var db = this.writableDatabase
        var values = ContentValues()
        values.put(DatabaseConstants.NAME_COL, name)
        values.put(DatabaseConstants.SCORE_COL, score)
        var results = db.insert(DatabaseConstants.TABLE_NAME, null, values)
        return results.toInt() != -1
    }

    fun deleteData():Boolean{
        var db = this.writableDatabase

        var result = db.delete(DatabaseConstants.TABLE_NAME, null, null)
        return result != -1
    }

    fun retrieveData():Cursor{
        var db = this.writableDatabase
        var cursor = db.rawQuery("SELECT * FROM ${DatabaseConstants.TABLE_NAME}", null)

        return cursor
    }
}