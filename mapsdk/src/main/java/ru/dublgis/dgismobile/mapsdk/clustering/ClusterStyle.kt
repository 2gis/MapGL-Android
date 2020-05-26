package ru.dublgis.dgismobile.mapsdk.clustering

import ru.dublgis.dgismobile.mapsdk.MarkerAnchor
import ru.dublgis.dgismobile.mapsdk.MarkerIconDescriptor
import ru.dublgis.dgismobile.mapsdk.MarkerSize

class ClusterStyle(
    val anchor: MarkerAnchor? = null,
    val hoverAnchor: MarkerAnchor? = null,
    val hoverIcon: MarkerIconDescriptor? = null,
    val hoverSize: MarkerSize? = null
)