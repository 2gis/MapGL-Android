package ru.dublgis.dgismobile.mapsdk.utils.location

import android.location.Location

interface LocationProvider {
    val location: Location?
    fun checkPermissionAndRequestLocation()
    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    )

}