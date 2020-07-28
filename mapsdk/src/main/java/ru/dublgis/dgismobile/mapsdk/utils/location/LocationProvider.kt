package ru.dublgis.dgismobile.mapsdk.utils.location

import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import ru.dublgis.dgismobile.mapsdk.utils.Permission.ACCESS_FINE_LOCATION

internal class LocationProvider(private val activity: Activity) {
    private val LOCATION_PERMISSION_REQUEST_ID: Int = 777

    private var locationProvider: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(activity)
    private lateinit var listener: LocationCallback

    var location: Location? = null

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_ID -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //requestLocation(LOC_PERM)
                }
            }
        }
    }

    private fun requestPermissions(permission: String) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(permission),
            LOCATION_PERMISSION_REQUEST_ID
        )

        //requestPermissions(permission)
    }

    private fun checkSelfPermission(permission: String): Boolean =
        (ContextCompat.checkSelfPermission(
            activity,
            permission
        ) != PackageManager.PERMISSION_GRANTED)

    private fun createLocationRequest(userLocationOptions: UserLocationOptions): LocationRequest =
        LocationRequest.create().apply {
            userLocationOptions.interval?.let {
                interval = it
            }
            userLocationOptions.fastestInterval?.let {
                interval = it
            }
            userLocationOptions.priority?.let {
                priority = it
            }
        }

    fun requestLocation(
        userLocationOptions: UserLocationOptions,
        locationCallback: LocationCallback
    ) {
        val permission = userLocationOptions.permissionOptions.permission
        if (checkSelfPermission(permission)) {
            requestPermissions(permission)
            return
        }

        val request = createLocationRequest(userLocationOptions)

        listener = object : LocationCallback() {
            override fun onLocationResult(res: LocationResult?) {
                res?.lastLocation?.let {
                    location = it
                }

                locationCallback.onLocationResult(res)
            }
        }
        locationProvider.requestLocationUpdates(request, listener, null)
    }

    fun removeLocationUpdates() {
        locationProvider.removeLocationUpdates(listener);
    }
}