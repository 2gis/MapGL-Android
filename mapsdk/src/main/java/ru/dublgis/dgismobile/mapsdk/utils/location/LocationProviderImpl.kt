package ru.dublgis.dgismobile.mapsdk.utils.location

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import ru.dublgis.dgismobile.mapsdk.LonLat

internal class LocationProviderImpl(private val activity: Activity) : LocationProvider {
    private val LOCATION_PERMISSION_REQUEST_ID: Int = 777
    private val LOC_PERM = Manifest.permission.ACCESS_FINE_LOCATION

    private var locationProvider: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(activity)
    override var location: Location? = null

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_ID -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    requestLocation()
                }
            }
        }
    }

    override fun checkPermissionAndRequestLocation() {
        val grant = ContextCompat.checkSelfPermission(activity, LOC_PERM)
        if (grant != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(LOC_PERM),
                LOCATION_PERMISSION_REQUEST_ID
            )
        } else {
            requestLocation()
        }
    }

    private fun requestLocation() {
        val grant = ContextCompat.checkSelfPermission(activity, LOC_PERM)
        if (grant != PackageManager.PERMISSION_GRANTED) {
            return
        }

        val request = LocationRequest.create()
        request.interval = 60000
        request.fastestInterval = 20000
        request.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val listener = object : LocationCallback() {
            override fun onLocationResult(res: LocationResult?) {
                res?.lastLocation?.let {
                    location = it
                }
            }
        }
        locationProvider.requestLocationUpdates(request, listener, null)
    }
}