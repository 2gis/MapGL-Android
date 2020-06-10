package ru.dublgis.dgismobile.mapsdk

/**
 * The event type for pointer-related map events.
 */
data class MapPointerEvent(
    /**
     * Geographical coordinates of the event.
     */
    val lngLat: LonLat,
    /**
     * Target (geographical object) of the event.
     */
    val target: MapObject?
)