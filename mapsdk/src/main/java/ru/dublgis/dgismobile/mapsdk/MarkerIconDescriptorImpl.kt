package ru.dublgis.dgismobile.mapsdk

import android.content.res.AssetManager
import android.util.Base64

internal class SvgMarkerIconDescriptor(
    private val am: AssetManager,
    private val path: String
)

    : MarkerIconDescriptor {
    override fun toJsFormat(): String {
        am.open(path).let {
            val buffer = Base64.encodeToString(
                it.readBytes(), Base64.NO_PADDING or Base64.NO_WRAP
            )

            return "data:image/svg+xml;base64,$buffer"
        }
    }
}