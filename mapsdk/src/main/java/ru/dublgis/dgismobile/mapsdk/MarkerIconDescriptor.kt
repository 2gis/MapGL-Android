package ru.dublgis.dgismobile.mapsdk

import android.content.res.AssetManager


abstract class MarkerIconDescriptor


fun iconFromSvgAsset(assetManager: AssetManager, path: String): MarkerIconDescriptor
        = SvgMarkerIconDescriptor(assetManager, path)
