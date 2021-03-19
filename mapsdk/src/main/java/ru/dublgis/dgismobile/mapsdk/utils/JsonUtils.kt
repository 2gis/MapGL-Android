package ru.dublgis.dgismobile.mapsdk.utils

import org.json.JSONArray
import org.json.JSONObject

internal fun <R> JSONArray.mapObjects(transform: (JSONObject) -> R): List<R> {
    val result = mutableListOf<R>()
    for (i in 0 until length()) {
        result.add(transform(getJSONObject(i)))
    }
    return result
}