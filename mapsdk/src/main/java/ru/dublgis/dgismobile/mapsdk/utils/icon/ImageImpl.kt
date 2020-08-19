package ru.dublgis.dgismobile.mapsdk.utils.icon

internal class ImageImpl(val buffer: String) : Image {
    override fun toJsFormat(): String {
        return buffer
    }
}