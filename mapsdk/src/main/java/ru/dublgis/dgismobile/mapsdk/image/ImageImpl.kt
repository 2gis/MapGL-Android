package ru.dublgis.dgismobile.mapsdk.image

internal class ImageImpl(val buffer: String) : Image {
    override fun toJsFormat(): String {
        return buffer
    }
}