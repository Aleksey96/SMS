package com.alexey.sms

import android.content.ContentResolver
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.fragment.app.ListFragment
import java.text.SimpleDateFormat


class SmsFragment : ListFragment() {

    private var list = ArrayList<MessageSms>()
    var cursor: Cursor? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        readSms()

        val adapter = SimpleCursorAdapter(
            inflater.context, R.layout.list_view_element, cursor,
            arrayOf("address"), intArrayOf(R.id.phoneNumber), 0
        )
        listAdapter = adapter

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun readSms() {

        val inboxURI: Uri = Uri.parse("content://sms/sent")
        val reqCols = arrayOf("_id", "address", "body", "date", "status")
        val cr: ContentResolver = context!!.contentResolver
        cursor = cr.query(inboxURI, reqCols, null, null, null)


        if (cursor!!.moveToFirst()) {
            do {
                val element = MessageSms(
                    cursor!!.getString(1),
                    cursor!!.getString(2),
                    cursor!!.getString(3),
                    cursor!!.getString(4)
                )
                list.add(element)
            } while (cursor!!.moveToNext())
        } else {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        var dateTime: String = sdf.format(cursor!!.getString(3).toLong())
        Log.i("info", dateTime)
        val phone = cursor!!.getString(1)
        Log.i("info", phone)
        val message = cursor!!.getString(2)
        Log.i("info", message)
        var status = cursor!!.getString(4)
        val intent = Intent(context, SMSActivity::class.java)
        intent.putExtra("phoneNumber", phone)
        intent.putExtra("message", message)
        intent.putExtra("dateTime", dateTime)
        when (status) {
            "-1" -> status = "Статус неизвестен"
            "0" -> status = "Доставлено"
            else -> "Недоставлено"
        }
        intent.putExtra("status", status)
        startActivity(intent)
        super.onListItemClick(l, v, position, id)

    }

    override fun onDestroy() {
        super.onDestroy()
        cursor!!.close()
    }
}
