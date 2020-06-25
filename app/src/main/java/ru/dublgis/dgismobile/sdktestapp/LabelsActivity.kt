package ru.dublgis.dgismobile.sdktestapp

import android.graphics.Color
import ru.dublgis.dgismobile.mapsdk.labels.Label
import ru.dublgis.dgismobile.mapsdk.labels.LabelOptions

class LabelsActivity : MapActivity() {

    private var label: Label? = null

    override fun onDGisMapReady() {
        showLabel()
    }

    private fun showLabel() {
        label =
            map?.createLabel(
                LabelOptions(
                    coordinates = map?.center!!,
                    text = "This is label",
                    color = Color.BLUE,
                    fontSize = 24f,
                    maxZoom = 14f,
                    minZoom = 10f,
                    anchor = 15.0 to 48.0
                )
            )

        map?.setOnClickListener {
            if (label?.isHidden!!)
                label?.show()
            else
                label?.hide()
        }
    }
}