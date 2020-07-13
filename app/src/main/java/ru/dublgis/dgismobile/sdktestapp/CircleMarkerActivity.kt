package ru.dublgis.dgismobile.sdktestapp

import android.widget.Toast
import ru.dublgis.dgismobile.mapsdk.geometries.circle.circlemarker.CircleMarkerOptions
import java.lang.ref.WeakReference

class CircleMarkerActivity : MapActivity() {
    override fun onDGisMapReady() {
        showCircleMarker()
    }

    private fun showCircleMarker() {
        val circleMarker = map?.createCircleMarker(
            CircleMarkerOptions(
                map?.center!!,
                20f
            )
        )

        val ctx = WeakReference(this)

        circleMarker?.setOnClickListener {
            ctx.get()?.let { activity ->

                circleMarker.destroy()

                val msg = "remove circleMarker"

                Toast.makeText(activity, msg, Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}
