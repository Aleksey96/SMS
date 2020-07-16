package com.alexey.sms

class DataBaseReader {
    /*lateinit var cursor:Cursor
var db:SQLiteDatabase?=null*/

    /* val list=listView

try {
    /*if(checkPermission()){
        readSms()
    }else{
        toast(resources.getString(R.string.message_permission))
    }*/
    val inboxURI: Uri = Uri.parse("content://sms/sent")

    val dataBaseHelper=SmsDataBaseHelper(this)
    db=dataBaseHelper.readableDatabase
    cursor=db!!.query(false,"content://sms/sent", arrayOf("_id", "address", "body","date","status"),
        null,null,null,null,null,null,null)
    val listAdapter=SimpleCursorAdapter(this,R.layout.list_view_element,cursor,
        arrayOf("address","date","status"),
        intArrayOf(R.id.phoneNumber,R.id.datetime,R.id.status),0)
    list.adapter=listAdapter

}
catch (e:SQLException){
    toast(resources.getString(R.string.data_base))
}

}

override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
if (cursor!!.moveToNext()) {
    val dateTime = cursor.getString(3)
    Log.i("info",dateTime)
    val phone = cursor.getString(1)
    Log.i("info",phone)
    val message = cursor.getString(2)
    Log.i("info",message)
    val intent = Intent(this, SMSActivity::class.java)
    intent.putExtra("phoneNumber", phone)
    intent.putExtra("message", message)
    intent.putExtra("dateTime", dateTime)
    startActivity(intent)
}
super.onListItemClick(l, v, position, id)
}

override fun onDestroy() {
super.onDestroy()
cursor!!.close()
db!!.close()
}


}

}private fun checkPermission():Boolean{
if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_SMS), REQUEST_PHONE_CALL)
return false
} else {
return true
}
}*/
}