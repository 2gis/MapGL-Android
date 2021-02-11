package ru.dublgis.dgismobile.mapsdk

/**
 * Options for Map.isSupported method.
 */
data class MapSupportOptions(
    /**
     * Causes isSupported method to return false if the performance of MapGL would be
     * dramatically worse than expected (i.e. a software renderer is would be used)
     */
    val failIfMajorPerformanceCaveat: Boolean
)
