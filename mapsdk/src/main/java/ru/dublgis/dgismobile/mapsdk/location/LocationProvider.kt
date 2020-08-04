package ru.dublgis.dgismobile.mapsdk.location

import android.location.Location
import androidx.lifecycle.LiveData


internal interface LocationProvider {
    val location: LiveData<Location>

    /**
     * Destroy the Location Provider.
     */
    fun destroy()
}
