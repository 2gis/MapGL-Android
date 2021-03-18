package ru.dublgis.dgismobile.sdktestapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ru.dublgis.dgismobile.mapsdk.floors.FloorPlan

@SuppressLint("ViewConstructor")
class FloorsControl(
    val floorPlan: FloorPlan,
    context: Context
) : LinearLayout(context, null, 0) {
    init {
        orientation = VERTICAL
        val views = mutableListOf<View>()
        setBackgroundColor(Color.WHITE)

        fun updateSelection() {
            views.forEachIndexed { index, it ->
                if (index == floorPlan.currentLevelIndex) {
                    it.setBackgroundResource(R.color.azure)
                } else {
                    it.setBackgroundColor(Color.TRANSPARENT)
                }
            }
        }

        floorPlan.levels.forEachIndexed { index, level ->
            addView(TextView(context).apply {
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 24.0f)
                text = " ${level.name} "

                setOnClickListener {
                    floorPlan.currentLevelIndex = index
                    updateSelection()
                }
                views.add(this)

            })
        }
        updateSelection()
    }
}
