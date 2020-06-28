package ru.dublgis.dgismobile.mapsdk

import android.graphics.Color
import androidx.core.graphics.alpha
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red

abstract class BaseOptions {
    protected fun jsColorFormat(color: Int): String {
        val argb = Color.parseColor(
            String.format(
                "#%08X",
                0xFFFFFFFF and color.toLong()
            )
        )

        val rgba = String.format(
            "#%02x%02x%02x%02x",
            argb.red,
            argb.green,
            argb.blue,
            argb.alpha
        )

        return rgba
    }
}