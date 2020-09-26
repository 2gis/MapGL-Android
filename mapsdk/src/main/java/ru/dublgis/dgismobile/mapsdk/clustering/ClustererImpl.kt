package ru.dublgis.dgismobile.mapsdk.clustering

import ru.dublgis.dgismobile.mapsdk.PlatformBridge
import java.lang.ref.WeakReference

internal class ClustererImpl(
    private val controller: WeakReference<PlatformBridge>,
    private val id: String,
    private val clustererOptions: ClustererOptions
) : Clusterer {

    val radius = clustererOptions.radius

    var onClick: ClustererClickCallback? = null
    var onDblClick: ClustererClickCallback? = null

    override fun destroy() {
        controller.get()?.destroyClusterer(id)
    }

    override fun load(clusterMarkers: Collection<InputMarker>) {
        controller.get()?.loadClustererMarkers(id, clusterMarkers)
    }

    override fun setOnClickListener(listener: ClustererClickCallback?) {
        onClick = listener
    }

    override fun setOnDblClickListener(listener: ClustererDblClickCallback?) {
        onDblClick = listener
    }
}