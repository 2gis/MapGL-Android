package ru.dublgis.dgismobile.mapsdk.location

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import ru.dublgis.dgismobile.mapsdk.utils.permissions.PermissionHandler
import ru.dublgis.dgismobile.mapsdk.utils.permissions.Permissions

const val LOCATION_PERMISSION_REQUEST_ID: Int = 777

@ExperimentalCoroutinesApi
internal class LocationProviderImpl(private val activity: Activity) : LocationProvider {

    private var locationProvider: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(activity)
    private lateinit var listener: LocationCallback

    override var location: MutableStateFlow<Location?> = MutableStateFlow(null)

    private fun requestPermissions(handler: PermissionHandler) {
        val permissions = arrayOf(ACCESS_FINE_LOCATION)
        Permissions().request(
            activity,
            permissions,
            LOCATION_PERMISSION_REQUEST_ID,
            handler
        )
    }

    private fun checkLocationPermission(): Boolean =
        (ContextCompat.checkSelfPermission(
            activity,
            ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED)

    private fun createLocationRequest(userLocationOptions: UserLocationOptions): LocationRequest =
        LocationRequest.create().apply {
            interval = 6000
            fastestInterval = 20000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

    override fun requestLocation(
        userLocationOptions: UserLocationOptions,
        locationCallback: LocationCallback,
        handler: PermissionHandler
    ) {
        if (checkLocationPermission()) {
            requestPermissions(handler)
            return
        }

        requestLocation(userLocationOptions, locationCallback)
    }

    @SuppressLint("MissingPermission")
    override fun requestLocation(
        userLocationOptions: UserLocationOptions,
        locationCallback: LocationCallback
    ) {
        val request = createLocationRequest(userLocationOptions)

        listener = locationCallback
        locationProvider.requestLocationUpdates(request, listener, null)
    }

    override fun destroy() {
        locationProvider.removeLocationUpdates(listener);
    }
}