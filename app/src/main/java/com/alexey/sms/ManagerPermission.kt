package com.alexey.sms

import android.app.Activity
import android.app.AlertDialog
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.alexey.sms.MainActivity.Companion.toast

class ManagerPermission(val activity: Activity,val list: List<String>,val code:Int) {

    private fun isPermissionsGranted():Int{
        var count=0
        for (permission in list){
            count=+ContextCompat.checkSelfPermission(activity,permission)
        }
        return  count
    }

    fun checkPermissions(){
        if(isPermissionsGranted()!=PackageManager.PERMISSION_GRANTED){
           showAlert()
        }
        else {
            activity.toast("Разрешения выданы")
        }
    }

    private fun showAlert(){
          AlertDialog.Builder(activity)
              .setTitle(R.string.dialogTitle)
              .setMessage(R.string.dialogMessage)
              .setPositiveButton(R.string.positiveAnswer,{dialog, which ->requestPermissions()})
    }

    private fun denitedPermissions():String{
        for(permission in list){
            if(ContextCompat.checkSelfPermission(activity,permission)== PackageManager.PERMISSION_DENIED)
                return permission
        }
        return ""
    }

    private  fun requestPermissions(){
       val permission=denitedPermissions()
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            activity.toast("Не получены необходимые разрешения")
        } else {
            ActivityCompat.requestPermissions(activity, list.toTypedArray(), code)
        }
    }

    fun processPermissionsResult(requestCode: Int, permissions: Array<String>,
                                 grantResults: IntArray): Boolean {
        var result = 0
        if (grantResults.isNotEmpty()) {
            for (item in grantResults) {
                result += item
            }
        }
        if (result == PackageManager.PERMISSION_GRANTED) return true
        return false
    }

}