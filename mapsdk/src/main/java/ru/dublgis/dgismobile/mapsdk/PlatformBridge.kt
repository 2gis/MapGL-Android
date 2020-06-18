package ru.dublgis.dgismobile.mapsdk

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import ru.dublgis.dgismobile.mapsdk.clustering.Clusterer
import ru.dublgis.dgismobile.mapsdk.clustering.ClustererImpl
import ru.dublgis.dgismobile.mapsdk.clustering.ClustererOptions
import ru.dublgis.dgismobile.mapsdk.clustering.InputMarker
import ru.dublgis.dgismobile.mapsdk.geometries.circle.Circle
import ru.dublgis.dgismobile.mapsdk.geometries.circle.CircleImpl
import ru.dublgis.dgismobile.mapsdk.geometries.circle.CircleOptions
import ru.dublgis.dgismobile.mapsdk.geometries.polygon.Polygon
import ru.dublgis.dgismobile.mapsdk.geometries.polygon.PolygonImpl
import ru.dublgis.dgismobile.mapsdk.geometries.polygon.PolygonOptions
import ru.dublgis.dgismobile.mapsdk.geometries.polyline.Polyline
import ru.dublgis.dgismobile.mapsdk.geometries.polyline.PolylineImpl
import ru.dublgis.dgismobile.mapsdk.geometries.polyline.PolylineOptions
import java.lang.ref.WeakReference
import kotlin.math.abs


typealias JsExecutor = (String) -> Unit
typealias MapReadyCallback = (Map?) -> Unit


internal class PlatformBridge(
    packageName: String,
    jsExecutor: JsExecutor,
    mapReadyCallback: MapReadyCallback
) : WebViewClient(), Map {

    private val packageName = packageName
    private val handler = Handler(Looper.getMainLooper())
    private val jsExecutor = jsExecutor
    private val readyCallback = mapReadyCallback

    private var onClickCallback: PointerChangeListener? = null
    private var onCenterChanged: PositionChangeListener? = null
    private var onPitchChanged: PropertyChangeListener? = null
    private var onZoomChanged: PropertyChangeListener? = null
    private var onRotationChanged: PropertyChangeListener? = null

    private val markers = mutableMapOf<String, MarkerImpl>()
    private val clusterers = mutableMapOf<String, ClustererImpl>()
    private val polylines = mutableMapOf<String, PolylineImpl>()
    private val polygons = mutableMapOf<String, PolygonImpl>()
    private val circles = mutableMapOf<String, CircleImpl>()

    private var _apiKey = ""
    private var _center = LonLat()

    private var _zoom: Double = 0.0
    private var _maxZoom: Double = 0.0
    private var _minZoom: Double = 0.0

    private var _pitch: Double = 0.0
    private var _maxPitch: Double = 0.0
    private var _minPitch: Double = 0.0

    private var _rotation: Double = 0.0

    private var _controls: Boolean = false

    private var clusterId = 0
    private var polylineId = 0
    private var polygonId = 0
    private var circleId = 0

    override var center: LonLat
        get() = _center
        set(value) {
            _center = value
            jsExecutor(
                """
                window.dgismap.map.setCenter(
                    [${value.lon}, ${value.lat}]
                );
            """
            )
        }

    override var zoom: Double
        get() = _zoom
        set(value) {
            _zoom = value
            jsExecutor(
                """
                window.dgismap.map.setZoom(${value});
            """
            )
        }

    override var maxZoom: Double
        get() = _maxZoom
        set(value) {
            _maxZoom = value
            jsExecutor(
                """
                window.dgismap.map.setMaxZoom($value);
            """
            )
        }

    override var minZoom: Double
        get() = _minZoom
        set(value) {
            _minZoom = value
            jsExecutor(
                """
                window.dgismap.map.setMinZoom($value);
            """
            )
        }

    override var maxPitch: Double
        get() = _maxPitch
        set(value) {
            _maxPitch = value
            jsExecutor(
                """
                window.dgismap.map.setMaxPitch($value);
            """
            )
        }

    override var minPitch: Double
        get() = _minPitch
        set(value) {
            _minPitch = value
            jsExecutor(
                """
                window.dgismap.map.setMinPitch($value);
            """
            )
        }

    override var pitch: Double
        get() = _pitch
        set(value) {
            _pitch = value
            jsExecutor(
                """
                window.dgismap.map.setPitch(${value});
            """
            )
        }

    override var rotation: Double
        get() = _rotation
        set(value) {
            _rotation = value
            jsExecutor(
                """
                window.dgismap.map.setRotation(${value});
            """
            )
        }

    override fun setOnClickListener(listener: PointerChangeListener?) {
        onClickCallback = listener
    }

    override fun setOnCenterChangedListener(listener: PositionChangeListener?) {
        onCenterChanged = listener
    }

    override fun setOnPitchChangedListener(listener: PropertyChangeListener?) {
        onPitchChanged = listener
    }

    override fun setOnRotationChangedListener(listener: PropertyChangeListener?) {
        onRotationChanged = listener
    }

    override fun setSelectedObjects(objects: Collection<MapObject>) {
        val arg = objects.joinToString(
            separator = ",",
            prefix = "[",
            postfix = "]",
            transform = {
                "'${it.id}'"
            }
        )
        jsExecutor(
            """
            window.dgismap.setSelectedObjects($arg);
        """
        )
    }

    override fun createClusterer(options: ClustererOptions): Clusterer {
        val id = "${clusterId++}"

        val clusterer = ClustererImpl(WeakReference(this), id, options)
        clusterers[id] = clusterer

        jsExecutor(
            """
            window.dgismap.createClusterer($id, ${clusterer.radius});
        """
        )

        return clusterer
    }

    fun loadClustererMarkers(id: String, inputMarkers: Collection<InputMarker>) {
        val arg = inputMarkers.joinToString(
            separator = ",",
            prefix = "[",
            postfix = "]",
            transform = {
                "{ coordinates: [${it.position.lon}, ${it.position.lat}] }"
            }
        )


        jsExecutor(
            """
            window.dgismap.loadClustererMarkers($id, $arg);
        """
        )
    }

    fun destroyClusterer(id: String) {
        jsExecutor(
            """
            window.dgismap.destroyClusterer($id);
        """
        )

        clusterers.remove(id)
    }

    override fun createPolyline(options: PolylineOptions): Polyline {
        val id = "${polylineId++}"

        val polyline =
            PolylineImpl(
                WeakReference(this),
                id
            )
        polylines[id] = polyline

        val arg = options.coordinates.joinToString(
            separator = ",",
            prefix = "[",
            postfix = ",]",
            transform = {
                "[${it.lon}, ${it.lat}]"
            }
        )

        jsExecutor(
            """
            window.dgismap.createPolyline($id, $arg);
        """
        )

        return polyline
    }

    override fun createPolygon(options: PolygonOptions): Polygon {
        val id = "${polygonId++}"

        val polygon =
            PolygonImpl(
                WeakReference(this),
                id
            )
        polygons[id] = polygon

        val arg = options.coordinates.joinToString(
            separator = ",",
            prefix = "[",
            postfix = ",]",
            transform = { it ->
                it.joinToString(
                    separator = ",",
                    prefix = "[",
                    postfix = ",]",
                    transform = {
                        "[${it.lon}, ${it.lat}]"
                    }
                )
            }
        )

        val color = String.format("#%06X", 0xFFFFFF and options.color)
        val strokeColor = String.format("#%06X", 0xFFFFFF and options.strokeColor)

        jsExecutor(
            """
            window.dgismap.createPolygon(
                $id, 
                $arg, 
                '$color', 
                ${options.strokeWidth}, 
                '$strokeColor'
            );
        """
        )

        return polygon
    }

    override fun createCircle(options: CircleOptions): Circle {
        val id = "${circleId++}"

        val circle =
            CircleImpl(
                WeakReference(this),
                id
            )
        circles[id] = circle

        jsExecutor(
            """
            window.dgismap.createCircle(
                $id, 
                [${options.coordinates.lon}, ${options.coordinates.lat}],
                ${options.radius}
            );
        """
        )

        return circle
    }

    fun destroyPolyline(id: String) {
        jsExecutor(
            """
            window.dgismap.destroyPolyline($id);
        """
        )

        polylines.remove(id)
    }

    fun destroyPolygon(id: String) {
        jsExecutor(
            """
            window.dgismap.destroyPolygon($id);
        """
        )

        polygons.remove(id)
    }

    fun destroyCircle(id: String) {
        jsExecutor(
            """
            window.dgismap.destroyCircle($id);
        """
        )

        circles.remove(id)
    }

    override fun setOnZoomChangedListener(listener: PropertyChangeListener?) {
        onZoomChanged = listener
    }

    override fun addMarker(options: MarkerOptions): Marker {
        val icon = if (options.icon != null) {
            (options.icon as MarkerIconDescriptorImpl).toJsFormat()
        } else {
            "https://d-assets.2gis.ru/markers/pin-opened.svg"
        }

        val fmtPair = { it: Pair<Double, Double> ->
            "[${it.first}, ${it.second}]"
        }

        val size = if (options.size != null) {
            fmtPair(options.size)
        } else "null"

        val anchor = if (options.size != null && options.anchor != null) {
            fmtPair(options.anchor)
        } else "null"

        val marker = MarkerImpl(WeakReference(this), options)

        markers[marker.id] = marker

        jsExecutor(
            """
            window.dgismap.createMarker(
                "${marker.id}",
                "$icon",
                [${marker.position.lon}, ${marker.position.lat}],
                $size,
                $anchor
            );
        """
        )
        return marker
    }

    override fun removeMarker(marker: Marker) {
        val impl = marker as MarkerImpl
        jsExecutor(
            """
            window.dgismap.removeMarker(
                "${impl.id}"
            );
        """
        )
        markers.remove(impl.id)
    }

    override fun hideMarker(marker: Marker) {
        val impl = marker as MarkerImpl
        jsExecutor(
            """
            window.dgismap.hideMarker(
                "${impl.id}"
            );
        """
        )
        impl.hidden = true
    }

    override fun showMarker(marker: Marker) {
        val impl = marker as MarkerImpl
        jsExecutor(
            """
            window.dgismap.showMarker(
                "${impl.id}"
            );
        """
        )
        impl.hidden = false
    }

    // private

    fun setMarkerCoordinates(marker: Marker, position: LonLat) {
        val impl = marker as MarkerImpl
        jsExecutor(
            """
            window.dgismap.setMarkerCoordinates(
                "${impl.id}", 
                [${position.lon}, ${position.lat}] 
            );
        """
        )
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        jsExecutor(
            """
            window.initMapGL(
                [${_center.lon}, ${_center.lat}],
                ${_maxZoom}, ${_minZoom}, ${_zoom},
                ${_maxPitch}, ${_minPitch}, ${_pitch},
                ${_rotation},
                "$_apiKey",
                "$packageName",
                $_controls
            );
        """
        )
    }

    fun setup(
        apiKey: String,
        center: LonLat,
        maxZoom: Double, minZoom: Double, zoom: Double,
        maxPitch: Double, minPitch: Double, pitch: Double,
        rotation: Double,
        controls: Boolean
    ) {
        _center = center
        _maxZoom = maxZoom
        _minZoom = minZoom
        _zoom = zoom
        _maxPitch = maxPitch
        _minPitch = minPitch
        _pitch = pitch
        _rotation = rotation
        _apiKey = apiKey
        _controls = controls
    }


    // called from JS thread -----------------------------------------------------------------------

    @JavascriptInterface
    fun onEvent(kind: String, payload: String) {
        Log.i(TAG, "from js $kind, payload $payload")
        handler.post {

            val parseLonLat = { payload: String ->
                val it = payload.split(';')
                LonLat(it[0].toDouble(), it[1].toDouble())
            }

            val parsePointer = { payload: String ->
                val it = payload.split(';')
                var target: MapObject? = null
                if (it.size == 3 && it[2].isNotEmpty()) {
                    target = mapObjectById(it[2])
                }
                MapPointerEvent(
                    LonLat(it[0].toDouble(), it[1].toDouble()),
                    target
                )
            }

            val parseDouble = { payload: String, def: Double ->
                try {
                    payload.toDouble()
                } catch (e: NumberFormatException) {
                    Log.e(TAG, "format exception")
                    def
                }
            }

            val isDifferent = { x: Double, y: Double ->
                abs(x - y) > 0.000001
            }

            when (kind) {
                "click" -> {
                    onClickCallback?.invoke(parsePointer(payload))
                }
                "markerClick" -> {
                    val marker = markers.get(payload)
                    marker?.onClick?.invoke()
                }
                "clustererClick" -> {
                    val clusterer = clusterers[payload]
                    clusterer?.onClick?.invoke()
                }
                "polylineClick" -> {
                    val polyline = polylines[payload]
                    polyline?.onClick?.invoke()
                }
                "polygonClick" -> {
                    val polygon = polygons[payload]
                    polygon?.onClick?.invoke()
                }

                "circleClick" -> {
                    val circle = circles[payload]
                    circle?.onClick?.invoke()
                }
                "centerend" -> {
                    val center = parseLonLat(payload)
                    if (_center != center) {
                        _center = center
                        onCenterChanged?.invoke(center)
                    }
                }
                "zoomend" -> {
                    val zoom = parseDouble(payload, _zoom)
                    if (isDifferent(_zoom, zoom)) {
                        _zoom = zoom
                        onZoomChanged?.invoke(zoom)
                    }
                }
                "pitched" -> {
                    val pitch = parseDouble(payload, _pitch)
                    if (isDifferent(_pitch, pitch)) {
                        _pitch = pitch
                        onPitchChanged?.invoke(pitch)
                    }
                }
                "rotationend" -> {
                    val rotation = parseDouble(payload, _rotation)
                    if (isDifferent(_rotation, rotation)) {
                        _rotation = rotation
                        onRotationChanged?.invoke(rotation)
                    }
                }
                "initialized" -> {
                    readyCallback(this)
                }
                else -> {
                    Log.w(TAG, "unexpected event type")
                }
            }
        }
    }
}