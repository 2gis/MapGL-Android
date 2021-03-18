package ru.dublgis.dgismobile.mapsdk.floors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class FloorLevel(val name: String)

interface FloorPlan {
    val id: String
    val levels: List<FloorLevel>
    val currentLevel: LiveData<FloorLevel>
    var currentLevelIndex: Int
}

internal class FloorPlanImpl(
    override val id: String,
    override val levels: List<FloorLevel>,
    currentLevelIndex: Int,
    val onLevelChanged: (Int) -> Unit
) : FloorPlan {
    override val currentLevel = MutableLiveData<FloorLevel>(levels[currentLevelIndex])
    override var currentLevelIndex: Int = currentLevelIndex
        get() = field
        set(value) {
            require(value >= 0 && value < levels.size) {
                "Invalid floor level index"
            }
            field = value
            currentLevel.value = levels[currentLevelIndex]
            onLevelChanged(value)
        }
}