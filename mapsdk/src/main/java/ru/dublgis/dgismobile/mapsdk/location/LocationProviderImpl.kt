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

internal class LocationProviderImpl(
    private val context: Context,
    private val options: UserLocationOptions
) : LocationProvider {

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

    private fun createLocationRequest(): LocationRequest =
        LocationRequest.create().apply {
            interval = options.interval
            fastestInterval = options.fastestInterval
            priority = options.priority.value
        }

    fun requestLocation() {
        if (checkLocationPermission()) {
            val handler = object : PermissionHandler {
                override fun onResult(
                    requestCode: Int,
                    grantedPermissions: Array<String>
                ) {
                    when (requestCode) {
                        LOCATION_PERMISSION_REQUEST_ID -> {
                            requestLocationUpdates()
                        }
                    }
                }
            }

            requestPermissions(handler)
            return
        }

        requestLocationUpdates()
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates() {
        val request = createLocationRequest()

        locationProvider.requestLocationUpdates(request, listener, null)
    }

    override val location: LiveData<Location>
        get() = _location

    private val _location = object : MutableLiveData<Location>() {
        override fun onActive() {
            super.onActive()
            requestLocationUpdates()
        }

        override fun onInactive() {
            super.onInactive()
            destroy()
        }
    }

    override fun destroy() {
        locationProvider.removeLocationUpdates(listener);
    }
}