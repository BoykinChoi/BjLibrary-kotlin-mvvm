package com.boykinchoi.read.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.boykinchoi.read.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)
        Toast.makeText(this, "fiuck", Toast.LENGTH_LONG).show()
    }

}




