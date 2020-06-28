package ru.dublgis.dgismobile.sdktestapp

import android.graphics.Color
import android.widget.Toast
import org.jetbrains.annotations.TestOnly
import ru.dublgis.dgismobile.mapsdk.LonLat
import ru.dublgis.dgismobile.mapsdk.geometries.polygon.PolygonOptions
import java.lang.ref.WeakReference

class PolygonActivity : MapActivity() {
    override fun onDGisMapReady() {
        showPolygon()
    }

    private fun showPolygon() {
        val polygon = map?.createPolygon(
            PolygonOptions(
                generateCoordinates(),
                Color.RED,
                10f,
                Color.BLACK
            )
        )
        polygon?.setOnClickListener {
            val ctx = WeakReference(this)
            ctx.get()?.let { activity ->

                polygon.destroy()

                val msg = "remove polygon"

                Toast.makeText(activity, msg, Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    @TestOnly
    private fun generateCoordinates(): Collection<Collection<LonLat>> {
        val coordinates1 = mutableListOf<LonLat>()
        coordinates1.add(LonLat(55.28770929, 25.22069944))
        coordinates1.add(LonLat(55.28976922, 25.25656786))
        coordinates1.add(LonLat(55.33302789, 25.25687836))
        coordinates1.add(LonLat(55.33096795, 25.22007825))
        coordinates1.add(LonLat(55.28770929, 25.22069944))

        val coordinates2 = mutableListOf<LonLat>()
        coordinates2.add(LonLat(55.29500489, 25.23979952))
        coordinates2.add(LonLat(55.31285768, 25.25175496))
        coordinates2.add(LonLat(55.32676225, 25.23917843))
        coordinates2.add(LonLat(55.31062608, 25.2279982))
        coordinates2.add(LonLat(55.29500489, 25.23979952))

        return mutableListOf<Collection<LonLat>>(coordinates1, coordinates2)
    }
}
