package ru.dublgis.dgismobile.mapsdk

import android.location.Location
import androidx.lifecycle.LiveData
import ru.dublgis.dgismobile.mapsdk.clustering.Clusterer
import ru.dublgis.dgismobile.mapsdk.clustering.ClustererOptions
import ru.dublgis.dgismobile.mapsdk.directions.Directions
import ru.dublgis.dgismobile.mapsdk.directions.DirectionsOptions
import ru.dublgis.dgismobile.mapsdk.floors.FloorPlan
import ru.dublgis.dgismobile.mapsdk.geometries.circle.Circle
import ru.dublgis.dgismobile.mapsdk.geometries.circle.CircleOptions
import ru.dublgis.dgismobile.mapsdk.geometries.circle.circlemarker.CircleMarker
import ru.dublgis.dgismobile.mapsdk.geometries.circle.circlemarker.CircleMarkerOptions
import ru.dublgis.dgismobile.mapsdk.geometries.polygon.Polygon
import ru.dublgis.dgismobile.mapsdk.geometries.polygon.PolygonOptions
import ru.dublgis.dgismobile.mapsdk.geometries.polyline.Polyline
import ru.dublgis.dgismobile.mapsdk.geometries.polyline.PolylineOptions
import ru.dublgis.dgismobile.mapsdk.labels.Label
import ru.dublgis.dgismobile.mapsdk.labels.LabelOptions
import ru.dublgis.dgismobile.mapsdk.location.UserLocationOptions


typealias PositionChangeListener = (LonLat) -> Unit
typealias PropertyChangeListener = (Double) -> Unit
typealias PointerChangeListener = (MapPointerEvent) -> Unit


interface Map {

    var center: LonLat
    var rotation: Double

    var maxZoom: Double
    var minZoom: Double
    var zoom: Double
    var styleZoom: Double

    var maxPitch: Double
    var minPitch: Double
    var pitch: Double

    val bounds: LonLatBounds

    val disableRotationByUserInteraction: Boolean
    val disablePitchByUserInteraction: Boolean

    val autoHideOSMCopyright: Boolean

    /**
     * The floor plan currently displayed on the map.
     */
    val floorPlan: LiveData<FloorPlan?>

    /**
     * Padding in density independent pixels from the different sides of the map canvas.
     * It influences map moving methods such as fitBounds.
     */
    var padding: Padding

    /**
     * The desired map language. Short language code "en", "ru", etc.
     */
    var language: String

    fun addMarker(options: MarkerOptions): Marker

    fun removeMarker(marker: Marker)

    fun hideMarker(marker: Marker)

    fun showMarker(marker: Marker)

    fun setOnClickListener(listener: PointerChangeListener?)

    fun setOnCenterChangedListener(listener: PositionChangeListener?)

    fun setOnPitchChangedListener(listener: PropertyChangeListener?)

    fun setOnZoomChangedListener(listener: PropertyChangeListener?)

    fun setOnStyleZoomChangedListener(listener: PropertyChangeListener?)

    fun setOnRotationChangedListener(listener: PropertyChangeListener?)

    fun setSelectedObjects(objects: Collection<MapObject>)

    fun createClusterer(options: ClustererOptions): Clusterer

    fun createPolyline(options: PolylineOptions): Polyline

    fun createPolygon(options: PolygonOptions): Polygon

    fun createCircle(options: CircleOptions): Circle

    fun createCircleMarker(options: CircleMarkerOptions): CircleMarker

    fun createLabel(options: LabelOptions): Label

    fun createDirections(options: DirectionsOptions): Directions

    fun enableUserLocation(options: UserLocationOptions)

    fun disableUserLocation()

    val userLocation: LiveData<Location>

    /**
     * Upload styles object by its id and apply it to the map.
     * @param style uuid of the style
     */
    fun setStyle(style: StyleId)

    /**
     * Sets whole map style global variables at once, any previously set variables will be reset or overriden.
     */
    fun setStyleState(styleState: StyleState)

    /**
     * Patches map style global variables. Use this method if you want to change a particular variable and leave other ones intact.
     */
    fun patchStyleState(styleState: StyleState)

    /**
     * Pans and zooms the map to contain its visible area within the specified geographical bounds.
     * This method also resets the map pitch and rotation to 0. But the map rotation can be saved
     * by option considerRotation.
     * @param bounds The geographical bounds to fit in
     * @param options FitBounds options
     */
    fun fitBounds(bounds: LonLatBounds, options: FitBoundsOptions? = null)

    /**
     * Tests whether the current browser supports MapGL.
     * Use our raster map implementation https://api.2gis.ru/doc/maps/en/quickstart/ if not.
     */
    fun isSupported(options: MapSupportOptions? = null): Boolean

    /**
     * Tests whether the current browser supports MapGL and returns the reason if not
     */
    fun notSupportedReason(options: MapSupportOptions? = null): String?
}
