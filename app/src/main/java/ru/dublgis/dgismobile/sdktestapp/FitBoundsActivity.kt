package ru.dublgis.dgismobile.sdktestapp

import android.content.res.Resources
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import ru.dublgis.dgismobile.mapsdk.LonLat
import ru.dublgis.dgismobile.mapsdk.LonLatBounds
import ru.dublgis.dgismobile.mapsdk.Padding
import ru.dublgis.dgismobile.mapsdk.geometries.polygon.PolygonOptions

class FitBoundsActivity() : MapActivity(Options(
    padding = Padding(left = 20.0f, right = 60.0f, top = 50.0f, bottom = 200.0f),
    zoom = 10.0
)) {
    override fun onDGisMapReady() {
        val bounds = LonLatBounds(
            northEast = LonLat(59.26278813143651, 30.86280146056587),
            southWest = LonLat(51.35263186969769, 19.267786148702257)
        )

        showPadding(map!!.padding)
        showBounds(bounds)

        addActionButton {
            map!!.fitBounds(bounds)
        }
    }

    private fun showPadding(padding: Padding) {
        fun Float.dpToPx(): Int {
            return (this * Resources.getSystem().displayMetrics.density).toInt()
        }

        val mapContainer = findViewById<FrameLayout>(R.id.map_container)
        mapContainer.addView(View(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            ).apply {
                leftMargin = padding.left.dpToPx()
                rightMargin = padding.right.dpToPx()
                topMargin = padding.top.dpToPx()
                bottomMargin = padding.bottom.dpToPx()
            }
            setBackgroundResource(R.drawable.border)
        })
    }

    private fun showBounds(bounds: LonLatBounds) {
        val points = listOf(
            bounds.southWest,
            LonLat(bounds.southWest.lon, bounds.northEast.lat),
            bounds.northEast,
            LonLat(bounds.northEast.lon, bounds.southWest.lat),
            bounds.southWest
        )
        map!!.createPolygon(
            PolygonOptions(
                listOf(points),
                Color.argb(20, 50, 20, 200),
                2f,
                Color.argb(150, 50, 20, 200)
            )
        )
    }
}
