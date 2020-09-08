package ru.dublgis.dgismobile.mapsdk

import android.location.Location
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import ru.dublgis.dgismobile.mapsdk.clustering.Clusterer
import ru.dublgis.dgismobile.mapsdk.clustering.ClustererImpl
import ru.dublgis.dgismobile.mapsdk.clustering.ClustererOptions
import ru.dublgis.dgismobile.mapsdk.clustering.InputMarker
import ru.dublgis.dgismobile.mapsdk.directions.CarRouteOptions
import ru.dublgis.dgismobile.mapsdk.directions.Directions
import ru.dublgis.dgismobile.mapsdk.directions.DirectionsImpl
import ru.dublgis.dgismobile.mapsdk.directions.DirectionsOptions
import ru.dublgis.dgismobile.mapsdk.geometries.circle.Circle
import ru.dublgis.dgismobile.mapsdk.geometries.circle.CircleImpl
import ru.dublgis.dgismobile.mapsdk.geometries.circle.CircleOptions
import ru.dublgis.dgismobile.mapsdk.geometries.circle.circlemarker.CircleMarker
import ru.dublgis.dgismobile.mapsdk.geometries.circle.circlemarker.CircleMarkerImpl
import ru.dublgis.dgismobile.mapsdk.geometries.circle.circlemarker.CircleMarkerOptions
import ru.dublgis.dgismobile.mapsdk.geometries.polygon.Polygon
import ru.dublgis.dgismobile.mapsdk.geometries.polygon.PolygonImpl
import ru.dublgis.dgismobile.mapsdk.geometries.polygon.PolygonOptions
import ru.dublgis.dgismobile.mapsdk.geometries.polyline.Polyline
import ru.dublgis.dgismobile.mapsdk.geometries.polyline.PolylineImpl
import ru.dublgis.dgismobile.mapsdk.geometries.polyline.PolylineOptions
import ru.dublgis.dgismobile.mapsdk.labels.Label
import ru.dublgis.dgismobile.mapsdk.labels.LabelImpl
import ru.dublgis.dgismobile.mapsdk.labels.LabelOptions
import ru.dublgis.dgismobile.mapsdk.location.LocationProvider
import ru.dublgis.dgismobile.mapsdk.location.LocationProviderFactory
import ru.dublgis.dgismobile.mapsdk.location.UserLocationOptions
import java.lang.ref.WeakReference
import kotlin.math.abs


typealias JsExecutor = (String) -> Unit
typealias MapReadyCallback = (Map?) -> Unit
typealias OnFinished = (Result<String>) -> Unit


internal class PlatformBridge(
    packageName: String,
    jsExecutor: JsExecutor,
    mapReadyCallback: MapReadyCallback,
    private val locationProviderFactory: LocationProviderFactory,
    private val lifecycleOwner: LifecycleOwner
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
    private val onFailureMap = mutableMapOf<String, OnFinished>()

    private val markers = mutableMapOf<String, MarkerImpl>()
    private val clusterers = mutableMapOf<String, ClustererImpl>()
    private val polylines = mutableMapOf<String, PolylineImpl>()
    private val polygons = mutableMapOf<String, PolygonImpl>()
    private val circles = mutableMapOf<String, CircleImpl>()
    private val circleMarkers = mutableMapOf<String, CircleMarkerImpl>()
    private val labels = mutableMapOf<String, LabelImpl>()
    private val directionsMap = mutableMapOf<String, DirectionsImpl>()

    private var userLocationMarker: CircleMarker? = null

    private var _apiKey = ""
    private var _center = LonLat()
    private var locationProvider: LocationProvider? = null

    private var _zoom: Double = 0.0
    private var _maxZoom: Double = 0.0
    private var _minZoom: Double = 0.0

    private var _pitch: Double = 0.0
    private var _maxPitch: Double = 0.0
    private var _minPitch: Double = 0.0

    private var _rotation: Double = 0.0

    private var _controls: Boolean = false
    private val interactiveCopyright: Boolean = false

    private var clusterId = 0
    private var polylineId = 0
    private var polygonId = 0
    private var circleId = 0
    private var circleMarkerId = 0
    private var labelId = 0
    private var markerId = 0
    private var directionsId = 0
    private var callbackId = 0

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

        val arg = options.toString()

        jsExecutor(
            """
            window.dgismap.createPolygon($id, $arg);
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

    override fun createCircleMarker(options: CircleMarkerOptions): CircleMarker {
        val id = "${circleMarkerId++}"

        val circleMarker =
            CircleMarkerImpl(
                WeakReference(this),
                id
            )
        circleMarkers[id] = circleMarker

        jsExecutor(
            """
            window.dgismap.createCircleMarker(
                $id, 
                [${options.coordinates.lon}, ${options.coordinates.lat}],
                ${options.radius}
            );
        """
        )

        return circleMarker
    }

    override fun createLabel(options: LabelOptions): Label {
        val id = "${labelId++}"

        val label = LabelImpl(WeakReference(this), id)
        labels[id] = label

        val arg = options.toString()

        jsExecutor(
            """
            window.dgismap.createLabel($id, $arg);
        """
        )

        return label
    }

    override fun createDirections(options: DirectionsOptions): Directions {
        val id = "${directionsId++}"

        val directions = DirectionsImpl(WeakReference(this), id)

        directionsMap[id] = directions

        jsExecutor(
            """
            window.dgismap.createDirections($id, $options);
        """
        )

        return directions
    }

    override fun enableUserLocation(options: UserLocationOptions) {
        disableUserLocation()

        locationProvider = locationProviderFactory.createLocationProvider(options)

        locationProvider?.let {

            mediatorUserLocation.addSource(it.location) { loc ->
                mediatorUserLocation.value = loc
            }

            observer = Observer { loc ->
                if (loc == null) {
                    hideUserLocation()
                    return@Observer
                }

                if (!options.isVisible) {
                    hideUserLocation()
                    return@Observer
                }

                showUserLocation(LonLat(loc.longitude, loc.latitude))

            }

            mediatorUserLocation.observe(lifecycleOwner, observer)
        }
    }

    override fun disableUserLocation() {
        if (mediatorUserLocation.hasObservers()) {
            locationProvider?.let {
                mediatorUserLocation.removeSource(it.location)
                mediatorUserLocation.removeObserver(observer)
                mediatorUserLocation.value = null
            }
        }
        locationProvider = null

        hideUserLocation()
    }

    private lateinit var observer: Observer<Location>

    private val mediatorUserLocation = MediatorLiveData<Location>()

    override val userLocation: LiveData<Location>
        get() = mediatorUserLocation

    private fun showUserLocation(position: LonLat) {
        userLocationMarker?.destroy()
        userLocationMarker = createCircleMarker(CircleMarkerOptions(position, 14f))
    }

    private fun hideUserLocation() {
        userLocationMarker?.destroy()
    }

    private fun carRoute(id: String, carRouteOptions: CarRouteOptions) {
        jsExecutor(
            """
            window.dgismap.carRoute($id, $carRouteOptions);
        """
        )
    }

    fun carRoute(
        id: String,
        carRouteOptions: CarRouteOptions,
        onFinished: OnFinished?
    ) {
        if (onFinished == null) {
            carRoute(id, carRouteOptions)
            return
        }

        val callbackId = "${this.callbackId++}"

        onFailureMap[callbackId] = onFinished
        jsExecutor(
            """
            window.dgismap.carRoute($id, $carRouteOptions, $callbackId);
        """
        )
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

    fun destroyCircleMarker(id: String) {
        jsExecutor(
            """
            window.dgismap.destroyCircleMarker($id);
        """
        )

        circleMarkers.remove(id)
    }

    fun destroyLabel(id: String) {
        jsExecutor(
            """
            window.dgismap.destroyLabel($id);
        """
        )

        labels.remove(id)
    }

    fun clearRoutes(id: String) {
        jsExecutor(
            """
            window.dgismap.clearRoutes($id);
        """
        )

        onFailureMap.remove("$callbackId")
    }

    fun destroyDirections(id: String) {
        jsExecutor(
            """
            window.dgismap.destroyDirections($id);
        """
        )

        directionsMap.remove(id)
    }

    fun showLabel(id: String) {
        jsExecutor(
            """
            window.dgismap.showLabel(
                $id
            );
        """
        )
    }

    fun hideLabel(id: String) {
        jsExecutor(
            """
            window.dgismap.hideLabel(
                $id
            );
        """
        )
    }

    override fun setOnZoomChangedListener(listener: PropertyChangeListener?) {
        onZoomChanged = listener
    }

    override fun addMarker(options: MarkerOptions): Marker {
        val marker = MarkerImpl(WeakReference(this), options, "${markerId++}")

        markers[marker.id] = marker

        val arg = options.toString()

        jsExecutor(
            """
            window.dgismap.createMarker("${marker.id}", $arg);
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
                $_controls,
                $interactiveCopyright
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
                "circleMarkerClick" -> {
                    val circleMarker = circleMarkers[payload]
                    circleMarker?.onClick?.invoke()
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
                "resultSuccess" -> {
                    val id = payload
                    onFailureMap.remove(id)
                }
                "resultFailure" -> {
                    val id = payload.substringBefore(" ")
                    val message = payload.substringAfter(" ")
                    onFailureMap[id]?.let {
                        it(Result.failure(Exception(message)))
                        onFailureMap.remove(id)
                    }
                }
                else -> {
                    Log.w(TAG, "unexpected event type")
                }
            }
        }
    }
}