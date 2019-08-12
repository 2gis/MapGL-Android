package ru.dublgis.dgismobile.sdktestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

typealias DGisMap = ru.dublgis.dgismobile.mapsdk.Map

class MainActivity : AppCompatActivity() {
    private var map: DGisMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
