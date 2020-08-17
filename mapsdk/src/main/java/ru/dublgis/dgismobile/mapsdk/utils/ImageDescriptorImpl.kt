package ru.dublgis.dgismobile.mapsdk.utils

import android.content.res.AssetManager
import android.util.Base64


internal abstract class ImageDescriptorImpl : ImageDescriptor() {
    abstract fun toJsFormat(): String
}

internal class SvgImageDescriptor(
    private val am: AssetManager,
    private val path: String
)

    : ImageDescriptorImpl() {
    override fun toJsFormat(): String {
        am.open(path).let {
            val buffer = Base64.encodeToString(
                it.readBytes(), Base64.NO_PADDING or Base64.NO_WRAP
            )

            return "data:image/svg+xml;base64,$buffer"
        }
    }
}