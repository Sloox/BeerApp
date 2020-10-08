package com.wrightstuff.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wrightstuff.beer.R

class MainActivity : AppCompatActivity() {
    override fun onBackPressed() {
        //ignore
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}