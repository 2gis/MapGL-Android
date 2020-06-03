package ru.dublgis.dgismobile.sdktestapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.markers).setOnClickListener {
            startActivity(Intent(this, MarkersActivity::class.java))
        }

        findViewById<Button>(R.id.clusterers).setOnClickListener {
            startActivity(Intent(this, ClusterersActivity::class.java))
        }
    }
}