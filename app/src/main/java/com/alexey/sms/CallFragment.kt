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
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import java.text.SimpleDateFormat
import android.widget.SimpleCursorAdapter


class CallFragment : ListFragment() {
    var cursor: Cursor? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        readCall()
        val adapter = SimpleCursorAdapter(
            inflater.context, android.R.layout.simple_list_item_1, cursor
            , arrayOf("number"), intArrayOf(android.R.id.text1), 0
        )
        listAdapter = adapter

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun readCall() {
        val cr: ContentResolver = context!!.contentResolver
        val inboxURI: Uri = Uri.parse("content://call_log/calls")
        val reqCols = arrayOf("_id", "number", "date")
        cursor = cr.query(inboxURI, reqCols, "date>?", arrayOf("1590958800000"), null)
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        val sdf = SimpleDateFormat("dd.MM.yyyy")
        var dateTime: String = sdf.format(cursor!!.getString(2).toLong())
        Log.i("info", dateTime)
        val phone = cursor!!.getString(1)
        Log.i("info", phone)
        val intent = Intent(context, CallActivity::class.java)
        intent.putExtra("phoneNumberCall", phone)
        intent.putExtra("dateTimeCall", dateTime)
        startActivity(intent)
        super.onListItemClick(l, v, position, id)

    }

    override fun onDestroy() {
        super.onDestroy()
        cursor!!.close()
    }
}
