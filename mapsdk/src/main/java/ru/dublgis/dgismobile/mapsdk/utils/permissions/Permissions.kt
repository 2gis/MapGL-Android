package ru.dublgis.dgismobile.mapsdk.utils.permissions

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat

const val ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION

class Permissions {
    fun request(
        context: Context, permissions: Array<String?>,
        requestCode: Int, handler: PermissionHandler
    ) {
        var permissions = permissions
        permissions = getNotGrantedPermissions(context, permissions)

        if (permissions.isEmpty()) {
            handler.onResult(requestCode, permissions)
            return
        }
        PermissionActivity.sPermissionHandler = handler
        val intent = Intent(context, PermissionActivity::class.java)
        intent.putExtra(PermissionActivity.EXTRA_PERMISSIONS, permissions)
        intent.putExtra(PermissionActivity.EXTRA_REQUEST_CODE, requestCode)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    private fun getNotGrantedPermissions(
        context: Context,
        permissions: Array<String?>
    ): Array<String?> {
        val result: ArrayList<String?> = ArrayList()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return result.toArray(arrayOf<String>())
        }
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    permission!!
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                result.add(permission)
            }
        }
        return result.toArray(arrayOf<String>())
    }

}