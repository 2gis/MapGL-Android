package ru.dublgis.dgismobile.mapsdk

import ru.dublgis.dgismobile.mapsdk.clustering.Clusterer
import ru.dublgis.dgismobile.mapsdk.clustering.ClustererOptions


typealias PositionChangeListener = (LonLat) -> Unit
typealias PropertyChangeListener = (Double) -> Unit
typealias PointerChangeListener = (MapPointerEvent) -> Unit


interface Map {

    var center: LonLat
    var rotation: Double

    var maxZoom: Double
    var minZoom: Double
    var zoom: Double

    var maxPitch: Double
    var minPitch: Double
    var pitch: Double

    fun addMarker(options: MarkerOptions): Marker

    fun removeMarker(marker: Marker)

    fun hideMarker(marker: Marker)

    fun showMarker(marker: Marker)

    fun setOnClickListener(listener: PointerChangeListener?)

    fun setOnCenterChangedListener(listener: PositionChangeListener?)

    fun setOnPitchChangedListener(listener: PropertyChangeListener?)

    fun setOnZoomChangedListener(listener: PropertyChangeListener?)

    fun setOnRotationChangedListener(listener: PropertyChangeListener?)

    fun setSelectedObjects(objects: Collection<MapObject>)

    fun createClusterer(options: ClustererOptions): Clusterer
}