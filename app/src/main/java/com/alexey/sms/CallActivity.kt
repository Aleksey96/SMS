package com.alexey.sms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CallActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)

        val phoneNumberView = findViewById<TextView>(R.id.phoneNumberCall)
        val dataTimeView = findViewById<TextView>(R.id.datetimeCall)

        val intent = intent

        dataTimeView.text = intent.getStringExtra("dateTimeCall")
        phoneNumberView.text = intent.getStringExtra("phoneNumberCall")
    }
}
