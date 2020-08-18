package ru.dublgis.dgismobile.mapsdk.utils.icon

import android.content.Context
import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream


class IconFactory(private val context: Context) {
    fun fromAsset(assetName: String): Icon {
        context.assets.open(assetName).let {
            val buffer = Base64.encodeToString(
                it.readBytes(), Base64.NO_PADDING or Base64.NO_WRAP
            )

            return IconImpl("data:image/svg+xml;base64,$buffer")
        }
    }

    fun fromBitmap(bitmap: Bitmap): Icon {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        val bytes = outputStream.toByteArray()
        val buffer = Base64.encodeToString(
            bytes, Base64.NO_PADDING or Base64.NO_WRAP
        )
        return IconImpl("data:image/png;base64,$buffer")
    }
}