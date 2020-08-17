package ru.dublgis.dgismobile.mapsdk.utils

import android.content.res.AssetManager

/**
 * Image icon.
 */
abstract class ImageDescriptor

/**
 * Get icon from assets
 *
 * @param assetManager Provides access to an application's raw asset files
 * @param path The name of the asset to open.
 */
fun iconFromSvgAsset(assetManager: AssetManager, path: String): ImageDescriptor =
    SvgImageDescriptor(assetManager, path)