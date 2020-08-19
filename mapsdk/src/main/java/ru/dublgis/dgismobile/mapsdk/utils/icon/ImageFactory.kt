package ru.dublgis.dgismobile.mapsdk.utils.icon

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream

/**
 * Factory for creating Images.
 */
class ImageFactory(private val context: Context) {

    /**
     * Provides an icon using the default marker icon used for Marker.
     */
    fun defaultMarker(): Image {
        return fromAsset("tooltip-big.svg")
    }

    /**
     * Creates an image using the name of a Bitmap image in the assets directory.
     */
    fun fromAsset(assetName: String): Image {
        context.assets.open(assetName).let {
            val buffer = Base64.encodeToString(
                it.readBytes(), Base64.NO_PADDING or Base64.NO_WRAP
            )

            return ImageImpl("data:image/svg+xml;base64,$buffer")
        }
    }

    /**
     * Creates an image from a given Bitmap image.
     */
    fun fromBitmap(bitmap: Bitmap): Image {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        val bytes = outputStream.toByteArray()
        val buffer = Base64.encodeToString(
            bytes, Base64.NO_PADDING or Base64.NO_WRAP
        )
        return ImageImpl("data:image/png;base64,$buffer")
    }

    /**
     * Creates an image using the resource ID of a Bitmap image.
     */
    fun fromResource(resourceId: Int): Image {
        val bitmap = BitmapFactory.decodeResource(context.resources, resourceId)
        return fromBitmap(bitmap)
    }

    /**
     * Create an Image using the name of a Bitmap image file located in the internal storage.
     */
    //TODO:
    fun fromFile(fileName : String) : Image {
        val bitmap = BitmapFactory.decodeFile(fileName)
        return fromBitmap(bitmap)
    }
}