package ru.dublgis.dgismobile.sdktestapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import ru.dublgis.dgismobile.mapsdk.LonLat
import ru.dublgis.dgismobile.mapsdk.Map
import ru.dublgis.dgismobile.mapsdk.location.UserLocationOptions
import kotlin.reflect.KClass
import ru.dublgis.dgismobile.mapsdk.MapFragment as DGisMapFragment


abstract class MapActivity : AppCompatActivity() {

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
            center = LonLat(55.30771, 25.20314),
            zoom = 12.0
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

    private fun onDGisMapReady(controller: Map?) {
        map = controller
        map?.enableUserLocation(UserLocationOptions())
        onDGisMapReady()
    }

    protected abstract fun onDGisMapReady()

    private fun centerMap(@Suppress("UNUSED_PARAMETER") view: View?) {
        map?.run {
            this.userLocation.observeOnce(this@MapActivity, Observer {
                center = LonLat(it.longitude, it.latitude)
                zoom = 16.0
            })
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
        // handle arrow click here
        if (item.itemId === android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val TEXT_EXTRA = "TEXT_EXTRA"
        fun startActivity(context: Context, text: String, kClass: KClass<out MapActivity>) {
            val intent = Intent(context, kClass.java)
            intent.putExtra(TEXT_EXTRA, text)
            context.startActivity(intent)
        }
    }

    private fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
        observe(lifecycleOwner, object : Observer<T> {
            override fun onChanged(t: T?) {
                observer.onChanged(t)
                removeObserver(this)
            }
        })
    }
}