package ru.dublgis.dgismobile.sdktestapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_geometries.*

class GeometriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geometries)

        polyline.setOnClickListener {
            startActivity(Intent(this, PolylineActivity::class.java))
        }

        polygon.setOnClickListener {
            startActivity(Intent(this, PolygonActivity::class.java))
        }

        circle.setOnClickListener {
            startActivity(Intent(this, CircleActivity::class.java))
        }

        circleMarker.setOnClickListener {
            startActivity(Intent(this, CircleMarkerActivity::class.java))
        }
    }
}