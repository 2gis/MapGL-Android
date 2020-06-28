package ru.dublgis.dgismobile.mapsdk.utils

import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red

object ColorUtils {
    fun jsColorFormat(color: Int): String {
        val rgba = String.format(
            "#%02x%02x%02x%02x",
            color.red,
            color.green,
            color.blue,
            color.alpha
        )

        return rgba
    }
}