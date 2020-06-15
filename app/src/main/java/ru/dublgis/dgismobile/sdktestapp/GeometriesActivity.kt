package ru.dublgis.dgismobile.sdktestapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class GeometriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geometries)

        findViewById<Button>(R.id.polyline).setOnClickListener {
            startActivity(Intent(this, PolylineActivity::class.java))
        }

        findViewById<Button>(R.id.polygon).setOnClickListener {
            startActivity(Intent(this, PolygonActivity::class.java))
        }

        findViewById<Button>(R.id.circle).setOnClickListener {
            startActivity(Intent(this, CircleActivity::class.java))
        }

        findViewById<Button>(R.id.carRoute).setOnClickListener {
            //TODO:
        }
    }
}