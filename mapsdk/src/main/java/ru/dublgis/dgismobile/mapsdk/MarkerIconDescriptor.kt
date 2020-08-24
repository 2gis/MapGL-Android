package ru.dublgis.dgismobile.mapsdk

import android.content.res.AssetManager

/**
 * Get icon from assets
 *
 * @param assetManager Provides access to an application's raw asset files
 * @param path The name of the asset to open.
 */
fun iconFromSvgAsset(assetManager: AssetManager, path: String): MarkerIconDescriptor =
    SvgMarkerIconDescriptor(assetManager, path)
