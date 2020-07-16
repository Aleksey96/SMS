package com.alexey.sms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SMSActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)

        val messageView = findViewById<TextView>(R.id.messageView)
        val phoneNumberView = findViewById<TextView>(R.id.phoneNumber1)
        val dataTimeView = findViewById<TextView>(R.id.datetime1)
        val status = findViewById<TextView>(R.id.act_status)

        val intent = intent


        messageView.text = intent.getStringExtra("message")
        dataTimeView.text = intent.getStringExtra("dateTime")
        phoneNumberView.text = intent.getStringExtra("phoneNumber")
        status.text = intent.getStringExtra("status")

    }
}
