package com.example.viewmodeldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val myViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MyViewModel::class.java);
        val myViewModel by viewModels<MyViewModel>()
        myViewModel.number.observe(this, Observer {
            findViewById<TextView>(R.id.textView).text = it.toString()
        })

        findViewById<Button>(R.id.button).setOnClickListener {
            myViewModel.addOne()
        }
    }
}