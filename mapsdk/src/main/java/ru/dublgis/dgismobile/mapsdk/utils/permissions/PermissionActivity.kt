package ru.dublgis.dgismobile.mapsdk.utils.permissions

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker
import java.util.*


class PermissionActivity : Activity() {
    private var mPermissionHandler: PermissionHandler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFinishOnTouchOutside(false)
        requestPermissions()
    }

    override fun onStop() {
        super.onStop()

        val intent = intent ?: return
        val requestCode =
            intent.getIntExtra(EXTRA_REQUEST_CODE, -1)
        if (requestCode == -1) {
            return
        }

        mPermissionHandler?.let {
            val grantedPermissions = arrayOf<String?>()
            it.onResult(requestCode, grantedPermissions)
        }


    }

    private fun requestPermissions() {
        mPermissionHandler = sPermissionHandler
        sPermissionHandler = null
        if (mPermissionHandler == null) {
            Log.e(TAG, "Invalid PermissionHandler")
            finish()
            return
        }
        val intent = intent
        if (intent == null) {
            Log.e(TAG, "Invalid intent")
            finish()
            return
        }
        val permissions =
            intent.getStringArrayExtra(EXTRA_PERMISSIONS)
        val requestCode =
            intent.getIntExtra(EXTRA_REQUEST_CODE, -1)
        if (permissions == null || requestCode == -1) {
            Log.e(TAG, "Invalid intent")
            finish()
            return
        }
        ActivityCompat.requestPermissions(this, permissions, requestCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (mPermissionHandler == null) {
            finish()
            return
        }
        val grantedPermissions =
            getGrantedPermissions(permissions, grantResults)
        mPermissionHandler!!.onResult(requestCode, grantedPermissions)
        mPermissionHandler = null
        finish()
    }

    companion object {
        const val EXTRA_PERMISSIONS = "permissions"
        const val EXTRA_REQUEST_CODE = "requestCode"
        var sPermissionHandler: PermissionHandler? = null
        private const val TAG = "Grym/PermissionActivity"
        private fun getGrantedPermissions(
            permissions: Array<String>,
            grantResults: IntArray
        ): Array<String?> {
            val result = ArrayList<String?>()
            var i = 0
            while (i < permissions.size && i < grantResults.size) {
                if (grantResults[i] == PermissionChecker.PERMISSION_GRANTED) {
                    result.add(permissions[i])
                }
                ++i
            }
            return result.toArray(arrayOf())
        }
    }
}
