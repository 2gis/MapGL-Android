package ru.dublgis.dgismobile.mapsdk.location

import android.content.Context

internal class LocationProviderFactory(private val context: Context) {

    fun createLocationProvider(
        options: UserLocationOptions
    ): LocationProvider {
        val locationProvider = LocationProviderImpl(context, options)
        locationProvider.requestLocation()

        return locationProvider
    }
}