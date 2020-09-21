package ru.dublgis.dgismobile.sdktestapp

import android.graphics.Color
import ru.dublgis.dgismobile.mapsdk.LonLat
import ru.dublgis.dgismobile.mapsdk.image.ImageFactory
import ru.dublgis.dgismobile.mapsdk.labels.LabelImage
import ru.dublgis.dgismobile.mapsdk.labels.LabelOptions

class LabelsActivity : MapActivity() {

    override fun onDGisMapReady() {
        showLabel()
    }

    private fun showLabel() {
        map?.createLabel(
            LabelOptions(
                coordinates = LonLat(55.32878, 25.23584),
                text = "Default"
            )
        )

        map?.createLabel(
            LabelOptions(
                coordinates = LonLat(55.32878, 25.24584),
                text = "Right",
                relativeAnchor = 0.0 to 0.5
            )
        )

        map?.createLabel(
            LabelOptions(
                coordinates = LonLat(55.30878, 25.22584),
                text = "Left",
                offset = -10.0 to 10.0,
                relativeAnchor = 1.0 to 1.0
            )
        )



        val labelImage = LabelImage(
            image = ImageFactory(this).fromAsset("tooltip.svg"),
            size = 100 to 50,
            stretchX = listOf(10 to 30, 50 to 90),
            stretchY = listOf(10 to 30),
            padding = listOf(10, 10, 20, 10)
        )

        map?.createLabel(
            LabelOptions(
                coordinates = LonLat(55.3252833, 25.2085033),
                text = "Label with image",
                image = labelImage
            )
        )
    }
}