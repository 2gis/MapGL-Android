package ru.dublgis.dgismobile.mapsdk.utils.icon

internal class IconImpl(val buffer: String) : Icon {
    override fun toJsFormat(): String {
        return buffer
    }
}