package ru.dublgis.dgismobile.mapsdk.clustering

import ru.dublgis.dgismobile.mapsdk.MarkerAnchor

typealias LabelAnchor = Pair<Double, Double>
typealias Size = Pair<Double, Double>

class ClustererStyle(
    val anchor: MarkerAnchor? = null,
    val icon: String? = null,
    val labelAnchor: LabelAnchor? = null,
    val labelColor: String? = null,
    val labelFontSize: Int? = null,
    val labelHaloColor: String? = null,
    val labelHaloRadius: Int? = null,
    val labelLetterSpacing: Int? = null,
    val labelText: String? = null,
    val size: Size? = null
)