package ru.dublgis.dgismobile.mapsdk.clustering

typealias ClustererClickCallback = () -> Unit

interface Clusterer {
    fun show(clusterMarkers: Collection<InputMarker>)

    fun hide()

    fun setOnClickListener(listener: ClustererClickCallback?)
}