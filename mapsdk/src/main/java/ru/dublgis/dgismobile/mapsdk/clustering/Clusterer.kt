package ru.dublgis.dgismobile.mapsdk.clustering

typealias ClustererClickCallback = () -> Unit

interface Clusterer {
    fun getId(): String

    fun load(clusterMarkers: Collection<InputMarker>)

    fun destroy()

    fun setOnClickListener(listener: ClustererClickCallback?)
}