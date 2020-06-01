package ru.dublgis.dgismobile.mapsdk.clustering

import ru.dublgis.dgismobile.mapsdk.PlatformBridge
import java.lang.ref.WeakReference
import java.util.*

internal class ClustererImpl(
    private val controller: WeakReference<PlatformBridge>,
    private val clustererOptions: ClustererOptions
) : Clusterer {

    val id = UUID.randomUUID().toString()

    val radius = clustererOptions.radius

    var onClick: ClustererClickCallback? = null

    override fun destroy() {
        controller.get()?.destroyClusterer(id)
    }

    override fun load(clusterMarkers: Collection<InputMarker>) {
        controller.get()?.loadClustererMarkers(id, clusterMarkers)
    }

    override fun setOnClickListener(listener: ClustererClickCallback?) {
        onClick = listener
    }
}