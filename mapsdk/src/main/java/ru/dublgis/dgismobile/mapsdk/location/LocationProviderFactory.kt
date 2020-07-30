package ru.dublgis.dgismobile.mapsdk.location

import android.app.Activity
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
internal class LocationProviderFactory(private val activity: Activity) {
    fun createLocationProvider(): LocationProvider = LocationProviderImpl(activity)
}