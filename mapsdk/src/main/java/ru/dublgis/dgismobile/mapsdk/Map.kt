package ru.dublgis.dgismobile.mapsdk

import android.content.Context
import android.util.Log
import android.webkit.WebView
import android.widget.FrameLayout


private val initPageHtml = """
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Blank</title>
        <style>
            html,
            body,
            #map {
                margin: 0;
                width: 100%;
                height: 100%;
                overflow: hidden;
            }
            .jakarta-hover {
                cursor: pointer;
            }
            .jakarta-rotating,
            .jakarta-inertia {
                cursor: move;
            }
            .jakarta-dragging {
                cursor: grabbing;
            }
        </style>
    </head>
    <body>
        <div id="map"></div>
        <script src="https://mapgl.2gis.com/api/js"></script>
        <script>
        
        
        
        
const container = document.getElementById('map');
window.map = new J.Map(container, {
    center: [37.618317, 55.750574],
    zoom: 16,
    minZoom: 2,
});

window.addEventListener('resize', () => window.map.invalidateSize());


        </script>
    </body>
</html>
"""



class Map(context: Context) : FrameLayout(context) {
    //    private val bridge = Platform()
    private val webView: WebView = WebView(context)

    init {
//        webView.addJavascriptInterface(bridge, "platform")
        webView.settings.javaScriptEnabled = true
        webView.loadData(initPageHtml, "text/html", null)

        addView(webView)

        // todo: subscribe page initialization
        // remove splash via opacity
//        val splash = View(context)
//        splash.setBackgroundColor(Color.parseColor("#f7f3df"))
//        addView(splash)
    }


    fun setCenter(lon: Double, lat: Double) {
        val js = """
            const point = [$lon, $lat];
            window.map.setCenter(point);
        """.trimIndent()
        Log.i("##", js)

        webView.evaluateJavascript(js, null)
    }
}
