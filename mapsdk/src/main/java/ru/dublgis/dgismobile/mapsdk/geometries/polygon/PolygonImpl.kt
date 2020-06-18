package ru.dublgis.dgismobile.mapsdk.geometries.polygon

import ru.dublgis.dgismobile.mapsdk.PlatformBridge
import java.lang.ref.WeakReference

internal class PolygonImpl(
    private val controller: WeakReference<PlatformBridge>,
    private val id: String
) : Polygon {

    var onClick: PolygonClickCallback? = null

    override fun destroy() {
        controller.get()?.destroyPolygon(id)
    }

    override fun setOnClickListener(listener: PolygonClickCallback?) {
        onClick = listener
    }
}