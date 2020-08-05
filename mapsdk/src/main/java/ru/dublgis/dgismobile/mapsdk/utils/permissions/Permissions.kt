package ru.dublgis.dgismobile.mapsdk.utils.permissions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat

internal class Permissions {
    fun request(
        context: Context, permissions: Array<String>,
        requestCode: Int, handler: PermissionHandler
    ) {
        getNotGrantedPermissions(context, permissions).let {
            if (it.isEmpty()) {
                handler.onResult(requestCode, it)
                return
            }
            PermissionActivity.sPermissionHandler = handler
            Intent(context, PermissionActivity::class.java).apply {
                putExtra(PermissionActivity.EXTRA_PERMISSIONS, it)
                putExtra(PermissionActivity.EXTRA_REQUEST_CODE, requestCode)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(this)
            }
        }
    }

    private fun getNotGrantedPermissions(
        context: Context,
        permissions: Array<String>
    ): Array<String> {
        val result: ArrayList<String> = ArrayList()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return result.toArray(arrayOf<String>())
        }
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                result.add(permission)
            }
        }
        return result.toArray(arrayOf<String>())
    }
}