package ru.dublgis.dgismobile.sdktestapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        markers.setOnClickListener {
            startActivity(Intent(this, MarkersActivity::class.java))
        }

        clusterers.setOnClickListener {
            startActivity(Intent(this, ClusterersActivity::class.java))
        }

        geometries.setOnClickListener {
            startActivity(Intent(this, GeometriesActivity::class.java))
        }

        labels.setOnClickListener {
            startActivity(Intent(this, LabelsActivity::class.java))
        }
    }
}