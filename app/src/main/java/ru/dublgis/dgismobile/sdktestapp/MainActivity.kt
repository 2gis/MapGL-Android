package ru.dublgis.dgismobile.sdktestapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.dublgis.dgismobile.mapsdk.MapFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        warmUpMap()
    }

    private fun warmUpMap() {
        val fakeMapFragment = supportFragmentManager.findFragmentById(R.id.fakeMapFragment)
                as MapFragment

        fakeMapFragment.setup(
            apiKey = resources.getString(R.string.dgis_map_key)
        )
    }
}