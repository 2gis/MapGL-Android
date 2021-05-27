package ru.dublgis.dgismobile.sdktestapp

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.Spinner
import ru.dublgis.dgismobile.mapsdk.LonLat
import ru.dublgis.dgismobile.mapsdk.LonLatBounds
import ru.dublgis.dgismobile.mapsdk.StyleId

private val languages = listOf("ru", "en")

class LanguageActivity() : MapActivity(options = Options(
    zoom = 3.0,
    language = languages.first()
)) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createLanguageSelectionControl()
    }

    private fun createLanguageSelectionControl() {
        val bottomControl = findViewById<FrameLayout>(R.id.bottom_control)
        Spinner(this).apply {
            setBackgroundResource(R.color.azure)
            adapter = ArrayAdapter(this@LanguageActivity, android.R.layout.simple_list_item_1, languages)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                    map?.language = languages[pos]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
            bottomControl.addView(this)
        }
    }
}
