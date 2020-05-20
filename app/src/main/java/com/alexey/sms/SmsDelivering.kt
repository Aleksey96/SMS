package com.alexey.sms

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class SmsDelivering:BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        when(resultCode){
            Activity.RESULT_OK->{
                Log.i(SmsSending.resultSms,"SMS DELIVERED")
            }
            Activity.RESULT_CANCELED->{
                Log.i(SmsSending.resultSms,"DELIVERY CANCELED")
            }
        }
    }
}
