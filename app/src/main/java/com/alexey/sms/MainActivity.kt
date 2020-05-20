package com.alexey.sms

import android.Manifest.permission.SEND_SMS
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsManager
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {

    private lateinit var managerPermissions: ManagerPermission
    companion object {
        val list = listOf(SEND_SMS)
        private val PERMISSION_REQUEST_CODE = 123
        fun Context.toast(message: String) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE ->{
                val isPermissionsGranted = managerPermissions
                    .processPermissionsResult(requestCode,permissions,grantResults)

                if(isPermissionsGranted){
                    toast("Разрешения предоставлены")
                }else{
                    toast("Разрешения отменены")
                }
                return
            }
        }
    }

    private fun initializePhoneNumberText(phoneNumberView:EditText):String=
        phoneNumberView
        .text
        .toString()

    private fun initializeMessageText(messageView: EditText):String=
        messageView
        .text
        .toString()

   private fun sentMessage(phoneNumberText:String,messageText:String){
       try {
           SmsManager.getDefault()
               .sendTextMessage(phoneNumberText, null,
                   messageText, null, null)
       }
       catch (e:IllegalArgumentException){
           Log.e("error",e.toString())
                toast("Поле(-я) не должно(-ы) быть пустым(-и)")
       }
       catch (er:SecurityException){
           managerPermissions.checkPermissions()
       }
       finally {
           //clouse your resource
       }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val setSmsAppIntent = Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT)
        setSmsAppIntent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, packageName)
        startActivity(setSmsAppIntent)
        //получаем системную службу для прослушивания телефона
        val telephonyManager=this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        managerPermissions = ManagerPermission(this,list, PERMISSION_REQUEST_CODE)
        managerPermissions.checkPermissions()
        val buttonSendSMS=findViewById<Button>(R.id.btnSendSMS)
        val phoneNumberView=findViewById<EditText>(R.id.phoneNumberText)
        val messageView=findViewById<EditText>(R.id.messageText)

        buttonSendSMS.setOnClickListener {

            val phoneNumberText=initializePhoneNumberText(phoneNumberView)
            val messageText=initializeMessageText(messageView)
            sentMessage(phoneNumberText,messageText)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != Activity.RESULT_OK){
            return
        }
        when(resultCode){
            123 -> {}//user allowed the app to become default
        }
    }
}
