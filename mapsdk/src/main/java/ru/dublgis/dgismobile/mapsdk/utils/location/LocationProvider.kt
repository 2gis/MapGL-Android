package ru.dublgis.dgismobile.mapsdk.utils.location

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import ru.dublgis.dgismobile.mapsdk.utils.permissions.PermissionHandler
import ru.dublgis.dgismobile.mapsdk.utils.permissions.Permissions

const val LOCATION_PERMISSION_REQUEST_ID: Int = 777


internal class LocationProvider(private val activity: Activity) {

    private var locationProvider: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(activity)
    private lateinit var listener: LocationCallback

    var location: Location? = null

    private fun requestPermissions(permission: String, handler: PermissionHandler) {
        val permissions = arrayOf<String?>(permission)
        Permissions().request(
            activity,
            permissions,
            LOCATION_PERMISSION_REQUEST_ID,
            handler
        )
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
        locationCallback: LocationCallback,
        handler: PermissionHandler
    ) {
        val permission = userLocationOptions.permissionOptions.permission
        if (checkSelfPermission(permission)) {
            requestPermissions(permission, handler)
            return
        }

        requestLocation(userLocationOptions, locationCallback)
    }

    @SuppressLint("MissingPermission")
    fun requestLocation(
        userLocationOptions: UserLocationOptions,
        locationCallback: LocationCallback
    ) {
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