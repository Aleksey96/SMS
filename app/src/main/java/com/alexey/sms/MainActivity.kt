package com.alexey.sms

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    private lateinit var phoneNumberView: EditText
    private lateinit var buttonSendSMS: Button
    private lateinit var messageView: EditText

    companion object {

        private const val PERMISSION_REQUEST_CODE = 123

        fun Context.toast(message: String) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }

    private fun initializePhoneNumberText(phoneNumberView: EditText): String =
        phoneNumberView.text.toString()

    private fun initializeMessageText(messageView: EditText): String =
        messageView.text.toString()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sms_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent: Intent
        when (item.itemId) {
            R.id.outcoming_message -> {
                intent = Intent(this, HistoryActivity::class.java)
                startActivity(intent)
            }
            R.id.call -> {
                if (hasPermission()) {
                    if (phoneNumberView.text.isNotEmpty()) {
                        intent =
                            Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumberView.text))
                        if (intent.resolveActivity(packageManager) != null)
                            startActivity(intent)
                    } else {
                        toast(resources.getString(R.string.call))
                    }
                } else
                    requestPermissionsWithRationale()
            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        phoneNumberView = findViewById(R.id.phoneNumberText)
        buttonSendSMS = findViewById(R.id.btnSendSMS)
        messageView = findViewById(R.id.messageText)

        buttonSendSMS.setOnClickListener {
            val phoneNumberText = initializePhoneNumberText(phoneNumberView)
            val messageText = initializeMessageText(messageView)
            if (hasPermission()) {
                sentMessage(phoneNumberText, messageText)
            } else {
                requestPermissionsWithRationale()
            }
        }
    }

    private fun sentMessage(phoneNumberText: String, messageText: String) {
        try {
            SmsManager.getDefault().sendTextMessage(
                phoneNumberText, null,
                messageText, null, null
            )

            phoneNumberView.setText("")
            messageView.setText("")
            toast(resources.getString(R.string.status_dispatch))
        } catch (e: IllegalArgumentException) {
            Log.e("error", e.toString())
            toast(resources.getString(R.string.warning))
        } catch (er: SecurityException) {
            toast(resources.getString(R.string.message_permission))
        } finally {
            //clouse your resource
        }
    }

    fun hasPermission(): Boolean {
        var results = 0
        val permissions = arrayOf(
            Manifest.permission.READ_SMS,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_CALL_LOG
        )
        permissions.forEach { results = checkCallingOrSelfPermission(it) }
        if (!(results == PackageManager.PERMISSION_GRANTED)) {
            return false
        }
        return true
    }

    fun requestPermissionsWithRationale() {
        if ((ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.READ_SMS
            ) ||
                    ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.CALL_PHONE
                    ))
        ) {
            toast(resources.getString(R.string.going_settings))
        } else {
            requestPerms()
        }
    }

    private fun requestPerms() {
        val permissions =
            arrayOf(Manifest.permission.READ_SMS, Manifest.permission.CALL_PHONE)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        var allowed = true
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> for (res in grantResults) {

                allowed = allowed && res == PackageManager.PERMISSION_GRANTED
            }
            else ->
                allowed = false
        }

        if (allowed) {

        } else {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_SMS) ||
                    shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)
                ) {
                    toast(resources.getString(R.string.message_permission))
                } else {
                    toast(resources.getString(R.string.going_settings))
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            //makeFolder()
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}


