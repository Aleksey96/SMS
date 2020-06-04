package com.alexey.sms

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*


class SmsDataBaseHelper(context: Context) :SQLiteOpenHelper(context,name,null, DATABASE_VERSION) {

   val _context:Context

    init {
        _context=context
    }

   private val createCommand="CREATE TABLE SMS (_id Integer PRIMARY KEY,NUMBERPHONE TEXT,MESSAGE TEXT, SENDINGTIME DATETIME)"
    private companion object{
        val name:String?="Sms.db"
        val contentValues=ContentValues()
        const val DATABASE_VERSION:Int=1
    }

    private fun getCurrentDateTime():String{
        val simpleDateFormat=SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDateTime=simpleDateFormat.format(Date())
        return currentDateTime

    }

        fun insertInfoSMS(db: SQLiteDatabase?,numberPhone:String,message:String){
        if (DATABASE_VERSION==1 && db!=null){
            contentValues.put("NUMBERPHONE",numberPhone)
            contentValues.put("MESSAGE",message)
            contentValues.put("SENDINGTIME",getCurrentDateTime())
            System.out.println(numberPhone)
            System.out.println(message)
            System.out.println(getCurrentDateTime())
            db.insert("SMS",null,contentValues)
        }
        else
            Toast.makeText(_context,"Значения не вставлены",Toast.LENGTH_LONG).show()
    }

    override fun onCreate(db: SQLiteDatabase?){
        db!!.execSQL(createCommand)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion>1)
           Toast.makeText(_context,"База данных устарела, обновите версию вашей базы данных",Toast.LENGTH_SHORT).show()
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
        if(oldVersion<1){
            Toast.makeText(_context,"Версии баз данных не совпадают",Toast.LENGTH_SHORT).show()
        }
    }


}