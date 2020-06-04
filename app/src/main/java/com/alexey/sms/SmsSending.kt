package com.alexey.sms

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.util.Log

class SmsSending:BroadcastReceiver() {
   /* companion object{
        val resultSms="SMS_RESULT"
    }*/

    override fun onReceive(context: Context?, intent: Intent?) {
        /*when(resultCode){
            Activity.RESULT_OK->{
                Log.i(resultSms,"SMS_sent")
            }
            SmsManager.RESULT_ERROR_GENERIC_FAILURE->{
                Log.i(resultSms,"Unknown problems")
            }
            SmsManager.RESULT_ERROR_RADIO_OFF->{
                Log.i(resultSms,"Module is down")
            }
            SmsManager.RESULT_ERROR_NULL_PDU->{
                Log.i(resultSms,"PDU error")
            }
        }*/
    }
}