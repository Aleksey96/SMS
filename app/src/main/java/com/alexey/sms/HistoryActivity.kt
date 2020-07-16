package com.alexey.sms

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_list.*

class HistoryActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val fragmentManager=PageAdapter(supportFragmentManager)
        viewpager.adapter=fragmentManager
        tablayout.setupWithViewPager(viewpager)
    }
}

