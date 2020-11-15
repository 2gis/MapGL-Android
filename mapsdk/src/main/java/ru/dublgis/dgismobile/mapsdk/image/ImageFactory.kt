package ru.dublgis.dgismobile.mapsdk.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.lang.IllegalArgumentException
import java.lang.RuntimeException

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
        val drawable = context.getDrawable(resourceId)
            ?: throw IllegalArgumentException("Failed to load bitmap resource $resourceId")

        val bitmap = if (drawable is BitmapDrawable) {
            drawable.bitmap
        } else {
            Bitmap
                .createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
                .also {
                    val canvas = Canvas(it)
                    drawable.setBounds(0, 0, canvas.width, canvas.height)
                    drawable.draw(canvas)
                }
        }
        return fromBitmap(bitmap)
    }

    /**
     * Create an Image using the name of a Bitmap image file located in the internal storage.
     */
    fun fromFile(file: File): Image {
        val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            ?: throw IOException("Can't load the image from file ${file.name}")

        return fromBitmap(bitmap)
    }
}