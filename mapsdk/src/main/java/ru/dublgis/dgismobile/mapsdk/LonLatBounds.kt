package ru.dublgis.dgismobile.mapsdk

import android.util.JsonWriter
import kotlin.math.max
import kotlin.math.min

/**
 * Geographical bounds.
 */
data class LonLatBounds(
    /**
     * The north-east point of the bounds [longitude, latitude].
     */
    val northEast: LonLat,
    /**
     * The south-west point of the bounds [longitude, latitude].
     */
    val southWest: LonLat
) : PlatformSerializable {
    override fun dump(writer: JsonWriter) {
        writer.apply {
            beginObject()
            name("northEast")
                northEast.dump(writer)
            name("southWest")
                southWest.dump(writer)
            endObject()
        }
    }

    /**
     * Extend the bounds to include a given point.
     */
    fun extend(point: LonLat): LonLatBounds {
        return LonLatBounds(
            southWest = LonLat(
                min(southWest.lon, point.lon),
                min(southWest.lat, point.lat)
            ),
            northEast = LonLat(
                max(northEast.lon, point.lon),
                max(northEast.lat, point.lat)
            )
        )
    }

    /**
     * Extend the bounds to include given points.
     */
    fun extend(points: List<LonLat>): LonLatBounds {
        var minLon = southWest.lon
        var minLat = southWest.lat
        var maxLon = northEast.lon
        var maxLat = northEast.lat

        points.forEach {
            minLon = min(minLon, it.lon)
            minLat = min(minLat, it.lat)
            maxLon = max(maxLon, it.lon)
            maxLat = max(maxLat, it.lat)
        }

        return LonLatBounds(
            southWest = LonLat(minLon, minLat),
            northEast = LonLat(maxLon, maxLat)
        )
    }
}
