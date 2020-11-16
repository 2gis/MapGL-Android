package ru.dublgis.dgismobile.mapsdk

import android.util.JsonWriter


internal interface IPlatformSerializable {
    fun dump(writer: JsonWriter)
}
