package ru.dublgis.dgismobile.mapsdk


typealias MarkerClickCallback = () -> Unit


interface Marker {
    var position: LonLat
    val isHidden: Boolean

    fun hide()

    fun show()

    fun setOnClickListener(listener: MarkerClickCallback?)
}