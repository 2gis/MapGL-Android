package ru.dublgis.dgismobile.sdktestapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.dialog_layout.view.*
import ru.dublgis.dgismobile.mapsdk.*
import ru.dublgis.dgismobile.mapsdk.Map
import ru.dublgis.dgismobile.mapsdk.location.UserLocationOptions
import kotlin.reflect.KClass
import ru.dublgis.dgismobile.mapsdk.MapFragment as DGisMapFragment

abstract class MapActivity(val options: Options = Options()) : AppCompatActivity() {
    data class Options(
        val center: LonLat? = null,
        val zoom: Double? = null,
        val style: StyleId? = null,
        val styleZoom: Double? = null,
        val minZoom: Double = 2.0,
        val maxZoom: Double = 18.0,
        val defaultBackgroundColor: Int? = null,
        val maxBounds: LonLatBounds? = null,
        val padding: Padding? = null)


    protected var map: Map? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.ActionBarAppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = intent.getStringExtra(TEXT_EXTRA)
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment)
                as DGisMapFragment

        val apiKey = resources.getString(R.string.dgis_map_key)

        mapFragment.mapReadyCallback = this::onDGisMapReady
        mapFragment.setup(
            apiKey = apiKey,
            center = options.center ?: LonLat(55.30771, 25.20314),
            zoom = options.zoom ?: 12.0,
            style = options.style,
            styleZoom = options.styleZoom,
            minZoom = options.minZoom,
            maxZoom = options.maxZoom,
            defaultBackgroundColor = options.defaultBackgroundColor,
            maxBounds = options.maxBounds,
            padding = options.padding
        )

        mapOf(
            R.id.zoom_in to this::zoomInMap,
            R.id.zoom_out to this::zoomOutMap,
            R.id.location to this::centerMap

        ).forEach {
            val btn = findViewById<ImageButton>(it.key)
            btn.setOnClickListener(it.value)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.information_menu, menu)
        return true
    }

    private fun onDGisMapReady(map: Map) {
        this.map = map
        map.enableUserLocation(UserLocationOptions(isVisible = true))
        map.userLocation.observe(this, Observer {})

        if (!map.isSupported()) {
            Log.e(TAG, "MapGL SDK is not supported: ${map.notSupportedReason()}")
        }
        onDGisMapReady()
    }

    protected open fun onDGisMapReady() {}

    private fun centerMap(@Suppress("UNUSED_PARAMETER") view: View?) {
        map?.run {
            this.userLocation.value?.let {
                center = LonLat(it.longitude, it.latitude)
                zoom = 16.0
            }
        }
    }

    private fun zoomInMap(@Suppress("UNUSED_PARAMETER") view: View?) {
        map?.run {
            zoom = zoom.inc()
        }
    }

    private fun zoomOutMap(@Suppress("UNUSED_PARAMETER") view: View?) {
        map?.run {
            zoom = zoom.dec()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId === android.R.id.home) {
            finish()
        } else if (item.itemId === R.id.information) {
            showInfoDialog()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showInfoDialog() {
        val builder = AlertDialog.Builder(this)
        val dialog: AlertDialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val customLayout = layoutInflater.inflate(R.layout.dialog_layout, null)
            .apply {
                title.text = getString(R.string.info_dialog_title)
                message.text = getString(R.string.info_dialog_message)
                buttonYes.apply {
                    text = getString(R.string.info_dialog_positive_text)
                    setOnClickListener {
                        val openURL = Intent(android.content.Intent.ACTION_VIEW)
                        openURL.data = Uri.parse(getString(R.string.info_url))
                        startActivity(openURL)
                        dialog.dismiss()
                    }
                }

                buttonNo.apply {
                    text = getString(R.string.info_dialog_negative_text)
                    setOnClickListener {
                        dialog.dismiss()
                    }
                }
            }


        dialog.setView(customLayout)
        dialog.show()
    }

    fun addActionButton(action: () -> Unit) {
        val bottomControl = findViewById<FrameLayout>(R.id.bottom_control)
        bottomControl.removeAllViews()
        FloatingActionButton(this).apply {
            setImageResource(R.drawable.ic_run)
            setOnClickListener {
                action()
            }
            bottomControl.addView(this)
        }
    }

    companion object {
        const val TEXT_EXTRA = "TEXT_EXTRA"
        fun startActivity(context: Context, text: String, kClass: KClass<out MapActivity>) {
            val intent = Intent(context, kClass.java)
            intent.putExtra(TEXT_EXTRA, text)
            context.startActivity(intent)
        }
    }
}