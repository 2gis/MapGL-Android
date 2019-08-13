package ru.dublgis.dgismobile.mapsdk

import android.graphics.Color
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment


class MapFragment: Fragment() {
    var mapReadyCallback : MapReadyCallback = { /* noop */ }

    private lateinit var bridge: PlatformBridge
    private var webView : WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bridge = PlatformBridge(
            context?.packageName ?: "",
            this::evaluateJavaScript,
            this::onMapReady
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_dgismap, null)

        webView = view.findViewById<WebView>(R.id.webview)?.apply {
            settings.javaScriptEnabled = true
            webViewClient = bridge

            setBackgroundColor(Color.parseColor("#f7f3df"))
            addJavascriptInterface(bridge, "bridge")
            loadData(loadIndexHtml(), "text/html","base64")
        }
        return view
    }

    fun setup(
        apiKey: String,
        center: LonLat = LonLat(37.618317, 55.750574),
        maxZoom: Double = 18.0,
        minZoom: Double = 2.0,
        zoom: Double = 16.0,
        maxPitch: Double = 45.0,
        minPitch: Double = 0.0,
        pitch: Double = 0.0,
        rotation: Double = 0.0,
        controls: Boolean = false

    ) = bridge.setup(
        apiKey,
        center,
        maxZoom, minZoom, zoom,
        maxPitch, minPitch, pitch,
        rotation,
        controls)

    // ------------------------------------------------------

    private fun onMapReady(map : Map?) {
        mapReadyCallback(map)
    }

    private fun evaluateJavaScript(script : String) {
        webView?.evaluateJavascript(script, null)
    }

    private fun loadIndexHtml(): String {
        val ctx = context ?: return ""

        ctx.assets.open("mapgl/index.html").let {
            return Base64.encodeToString(it.readBytes(), Base64.NO_PADDING or Base64.NO_WRAP)
        }
    }
}