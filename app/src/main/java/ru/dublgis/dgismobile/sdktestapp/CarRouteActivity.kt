package ru.dublgis.dgismobile.sdktestapp

import org.jetbrains.annotations.TestOnly
import ru.dublgis.dgismobile.mapsdk.LonLat
import ru.dublgis.dgismobile.mapsdk.geometries.carroute.Segment

class CarRouteActivity : MapActivity() {
    override fun onDGisMapReady() {
        showCarRoute()
    }

    private fun showCarRoute() {
        val carRoute = map?.createCarRoute(
            generateSegments()
        )
    }

    @TestOnly
    private fun generateSegments(): Collection<Segment> {
        val segments = mutableListOf<Segment>()
        segments.add(
            Segment(
                "'#e84646'", "'A'",
                mutableListOf(
                    map!!.center,
                    LonLat(55.29970489, 25.26853913),
                    LonLat(55.2994345, 25.26920691),
                    LonLat(55.29950714, 25.26936478)
                )
            )
        )
        segments.add(
            Segment(
                color = "'#e3e340'",
                coords = mutableListOf(
                    LonLat(55.29950714, 25.26936478),
                    LonLat(55.30124581, 25.26959538),
                    LonLat(55.30141272, 25.26965618),
                    LonLat(55.30191503, 25.26896923)
                )
            )
        )
        segments.add(
            Segment(
                "'#43e843'", "'B'",
                mutableListOf(
                    LonLat(55.30191503, 25.26896923),
                    LonLat(55.3020634, 25.26892939),
                    LonLat(55.30233927, 25.26823968)
                )
            )
        )

        return segments
    }

}
