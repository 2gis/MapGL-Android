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

private data class NamedStyle(val style: StyleId, val name: String)

private val styles = listOf(
    NamedStyle(StyleId("e05ac437-fcc2-4845-ad74-b1de9ce07555"), "Dark"),
    NamedStyle(StyleId("c080bb6a-8134-4993-93a1-5b4d8c36a59b"), "Light")
)

class StyleActivity() : MapActivity(options = Options(
    style = styles.first().style,
    defaultBackgroundColor = 0xff1c2429.toInt(),
    styleZoom = 12.0,
    maxBounds = LonLatBounds(
        northEast = LonLat(59.26278813143651, 30.86280146056587),
        southWest = LonLat(51.35263186969769, 19.267786148702257)
    )
)) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createStyleSelectionControl()
    }

    private fun createStyleSelectionControl() {
        val bottomControl = findViewById<FrameLayout>(R.id.bottom_control)
        val spinner = Spinner(this)
        spinner.setBackgroundResource(R.color.azure)
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, styles.map { it.name })
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                map?.setStyle(styles[pos].style)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        bottomControl.addView(spinner)
    }
}
