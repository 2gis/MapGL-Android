package ru.dublgis.dgismobile.mapsdk.location

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.*
import ru.dublgis.dgismobile.mapsdk.utils.permissions.PermissionHandler
import ru.dublgis.dgismobile.mapsdk.utils.permissions.Permissions

internal const val LOCATION_PERMISSION_REQUEST_ID: Int = 777

internal class LocationProviderImpl(private val context: Context) : LocationProvider {

    private var locationProvider: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    private val listener: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            _location.value = result.lastLocation
        }
    }

    private fun requestPermissions(handler: PermissionHandler) {
        val permissions = arrayOf(ACCESS_FINE_LOCATION)
        Permissions().request(
            context,
            permissions,
            LOCATION_PERMISSION_REQUEST_ID,
            handler
        )
    }

    private fun checkLocationPermission(): Boolean =
        (ContextCompat.checkSelfPermission(
            context,
            ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED)

    private fun createLocationRequest(userLocationOptions: UserLocationOptions): LocationRequest =
        LocationRequest.create().apply {
            interval = userLocationOptions.interval
            fastestInterval = userLocationOptions.fastestInterval
            priority = userLocationOptions.priority.value
        }

    fun requestLocation(
        userLocationOptions: UserLocationOptions
    ) {
        if (checkLocationPermission()) {
            val handler = object : PermissionHandler {
                override fun onResult(
                    requestCode: Int,
                    grantedPermissions: Array<String>
                ) {
                    when (requestCode) {
                        LOCATION_PERMISSION_REQUEST_ID -> {
                            requestLocationUpdates(userLocationOptions)
                        }
                    }
                }
            }

            requestPermissions(handler)
            return
        }

        requestLocationUpdates(userLocationOptions)
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates(
        userLocationOptions: UserLocationOptions
    ) {
        val request = createLocationRequest(userLocationOptions)

        locationProvider.requestLocationUpdates(request, listener, null)
    }

    override val location: LiveData<Location>
        get() = _location

    private val _location = MutableLiveData<Location>()

    override fun destroy() {
        locationProvider.removeLocationUpdates(listener);
    }
}