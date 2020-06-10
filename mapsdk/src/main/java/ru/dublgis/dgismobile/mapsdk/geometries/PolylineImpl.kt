package ru.dublgis.dgismobile.mapsdk.geometries

import ru.dublgis.dgismobile.mapsdk.PlatformBridge
import java.lang.ref.WeakReference

internal class PolylineImpl(
    private val controller: WeakReference<PlatformBridge>,
    private val id: String
) : Polyline {

    var onClick: PolylineClickCallback? = null

    override fun destroy() {
        controller.get()?.destroyPolyline(id)
    }

    override fun setOnClickListener(listener: PolylineClickCallback?) {
        onClick = listener
    }
}