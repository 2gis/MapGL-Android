package ru.dublgis.dgismobile.sdktestapp

import android.widget.Toast
import org.jetbrains.annotations.TestOnly
import ru.dublgis.dgismobile.mapsdk.LonLat
import ru.dublgis.dgismobile.mapsdk.clustering.ClustererOptions
import ru.dublgis.dgismobile.mapsdk.clustering.InputMarker
import java.lang.ref.WeakReference

class ClusterersActivity : MapActivity() {
    override fun onDGisMapReady() {
        showNewClusterer(generate1Markers())
        showNewClusterer(generate2Markers())
    }

    private fun showNewClusterer(clusterMarkers: Collection<InputMarker>) {
        val clusterer = map?.createClusterer(ClustererOptions(60f))
        clusterer?.load(clusterMarkers)

        val ctx = WeakReference(this)

        clusterer?.setOnClickListener {
            ctx.get()?.let { activity ->

                clusterer.destroy()

                val msg = "remove cluster"

                Toast.makeText(activity, msg, Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    @TestOnly
    private fun generate1Markers(): Collection<InputMarker> {
        val markersList = mutableListOf<InputMarker>()
        markersList.add(InputMarker(LonLat(55.30771, 25.20314)))
        markersList.add(InputMarker(LonLat(55.30762, 25.20413)))
        markersList.add(InputMarker(LonLat(55.30750, 25.20215)))
        markersList.add(InputMarker(LonLat(55.30742, 25.20515)))
        markersList.add(InputMarker(LonLat(55.30730, 25.20113)))

        return markersList
    }

    @TestOnly
    private fun generate2Markers(): Collection<InputMarker> {
        val markersList = mutableListOf<InputMarker>()
        markersList.add(InputMarker(LonLat(55.31671, 25.20214)))
        markersList.add(InputMarker(LonLat(55.31862, 25.20513)))
        markersList.add(InputMarker(LonLat(55.31650, 25.20315)))
        markersList.add(InputMarker(LonLat(55.31542, 25.20615)))

        return markersList
    }
}