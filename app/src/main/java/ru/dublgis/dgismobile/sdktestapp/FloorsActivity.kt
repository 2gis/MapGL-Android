package ru.dublgis.dgismobile.sdktestapp

import android.widget.*
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_map.*
import ru.dublgis.dgismobile.mapsdk.LonLat

class FloorsActivity() : MapActivity(options = Options(
    center = LonLat(37.625028262824884, 55.76020915917725),
    zoom = 18.2,
    maxZoom = 19.0
)) {
    override fun onDGisMapReady() {
        createFloorsControl()
    }

    private fun createFloorsControl() {
        val leftControl = findViewById<FrameLayout>(R.id.left_control)
        map!!.floorPlan.observe(this, Observer {
            leftControl.removeAllViews()
            it ?: return@Observer
            leftControl.addView(FloorsControl(it, this))
        })
    }
}
