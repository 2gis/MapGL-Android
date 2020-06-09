package ru.dublgis.dgismobile.mapsdk.clustering

typealias ClustererClickCallback = () -> Unit

/**
 * A class that provides markers clustering functionality.
 */
interface Clusterer {

    /**
     * Loads markers to clusterer.
     * @param clusterMarkers - An array of markers.
     */
    fun load(clusterMarkers: Collection<InputMarker>)

    /**
     * If the clusterer is no longer needed you can destroy it by using the destroy method:
     */
    fun destroy()

    /**
     * On click event
     */
    fun setOnClickListener(listener: ClustererClickCallback?)
}