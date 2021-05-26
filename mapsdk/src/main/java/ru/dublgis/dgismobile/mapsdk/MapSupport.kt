package ru.dublgis.dgismobile.mapsdk

import org.json.JSONObject

internal class MapSupport(
    val notSupportedReason: String?,
    val notSupportedWithGoodPerformanceReason: String?)
{
    companion object {
        fun fromJson(json: JSONObject): MapSupport {
            return MapSupport(
                notSupportedReason = json.opt("notSupportedReason") as String?,
                notSupportedWithGoodPerformanceReason = json.opt("notSupportedWithGoodPerformanceReason") as String?
            )
        }
    }
}
