package com.training.calculationchallengev2.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.training.calculationchallengev2.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }
}