package ru.dublgis.dgismobile.mapsdk

abstract class BaseOptions {
    protected fun hexColorFormat(color: Int): String = String.format(
        "#%08X",
        0xFFFFFFFF and color.toLong()
    )
}