package ru.dublgis.dgismobile.mapsdk.location

import android.location.Location
import com.google.android.gms.location.LocationCallback
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import ru.dublgis.dgismobile.mapsdk.utils.permissions.PermissionHandler


interface LocationProvider {
    @ExperimentalCoroutinesApi
    val location: MutableStateFlow<Location?>

    fun requestLocation(
        userLocationOptions: UserLocationOptions,
        locationCallback: LocationCallback,
        handler: PermissionHandler
    )

    fun requestLocation(
        userLocationOptions: UserLocationOptions,
        locationCallback: LocationCallback
    )

    fun destroy()
}
