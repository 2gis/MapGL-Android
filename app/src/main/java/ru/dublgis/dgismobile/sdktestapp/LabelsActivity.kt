package ru.dublgis.dgismobile.sdktestapp

import ru.dublgis.dgismobile.mapsdk.LonLat
import ru.dublgis.dgismobile.mapsdk.MarkerOptions
import ru.dublgis.dgismobile.mapsdk.image.ImageFactory
import ru.dublgis.dgismobile.mapsdk.labels.LabelImage
import ru.dublgis.dgismobile.mapsdk.labels.LabelOptions

class LabelsActivity : MapActivity() {


    override fun onDGisMapReady() {
        map?.apply {
            center = LonLat(55.32078, 25.22784)
            zoom = 13.0
        }

        showLabels()
        showPlacementAnchors()
    }

    private fun showLabels() {
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
            stretchX = listOf(10 to 40, 60 to 90),
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

    private fun showPlacementAnchors() {
        // The markers below are only for visualizing the geo-coordinates of the labels.
        // You don't need them in common.

        map?.apply {
            addMarker(MarkerOptions(
                position = LonLat(55.32878, 25.23584),
                icon = ImageFactory(applicationContext).fromAsset("anchor.svg")
            ))
            addMarker(MarkerOptions(
                position = LonLat(55.3287, 25.24584),
                icon = ImageFactory(applicationContext).fromAsset("anchor.svg")
            ))
            addMarker(MarkerOptions(
                position = LonLat(55.30878, 25.22584),
                icon = ImageFactory(applicationContext).fromAsset("anchor.svg")
            ))
        }
    }
}