package ru.dublgis.dgismobile.mapsdk.floors

import org.json.JSONObject
import ru.dublgis.dgismobile.mapsdk.utils.mapObjects

internal data class FloorPlanShowEvent(val id: String, val levels: List<FloorLevel>, val currentLevelIndex: Int) {
    val isValid: Boolean
        get() = !id.isEmpty() && currentLevelIndex >= 0 && currentLevelIndex < levels.size

    companion object {
        fun fromJson(json: JSONObject): FloorPlanShowEvent {
            return FloorPlanShowEvent(
                id = json.getString("floorPlanId"),
                currentLevelIndex = json.getInt("currentFloorLevelIndex"),
                levels = json.getJSONArray("floorLevels").mapObjects {
                    FloorLevel(it.getString("floorLevelName"))
                }
            )
        }
    }
}

internal data class FloorPlanHideEvent(val id: String) {
    val isValid: Boolean
        get() = !id.isEmpty()

    companion object {
        fun fromJson(json: JSONObject): FloorPlanHideEvent {
            return FloorPlanHideEvent(
                id = json.getString("floorPlanId")
            )
        }
    }
}