package ru.dublgis.dgismobile.mapsdk.clustering

import ru.dublgis.dgismobile.mapsdk.PlatformBridge
import java.lang.ref.WeakReference
import kotlin.math.absoluteValue
import kotlin.random.Random

internal class ClustererImpl(
    private val controller: WeakReference<PlatformBridge>,
    private val clustererOptions: ClustererOptions
) : Clusterer {

    //TODO:
    val id = "${Random.nextInt().absoluteValue}"
    val radius = clustererOptions.radius

    var onClick: ClustererClickCallback? = null

    override fun hide() {
        controller.get()?.destroyCluster(id)
    }

    override fun show(clusterMarkers: Collection<InputMarker>) {
        controller.get()?.addClusterMarkers(id, clusterMarkers)
    }

    override fun setOnClickListener(listener: ClustererClickCallback?) {
        onClick = listener
    }
}