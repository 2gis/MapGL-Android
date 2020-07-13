package ru.dublgis.dgismobile.sdktestapp

import android.widget.Toast
import ru.dublgis.dgismobile.mapsdk.geometries.circle.CircleOptions
import java.lang.ref.WeakReference

class CircleActivity : MapActivity() {
    override fun onDGisMapReady() {
        showCircle()
    }

    private fun showCircle() {
        val circle = map?.createCircle(
            CircleOptions(
                map?.center!!,
                2000f
            )
        )

        val ctx = WeakReference(this)

        circle?.setOnClickListener {
            ctx.get()?.let { activity ->

                circle.destroy()

                val msg = "remove circle"

                Toast.makeText(activity, msg, Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}
