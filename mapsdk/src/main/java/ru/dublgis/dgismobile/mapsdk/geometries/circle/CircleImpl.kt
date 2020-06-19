package ru.dublgis.dgismobile.mapsdk.geometries.circle

import ru.dublgis.dgismobile.mapsdk.PlatformBridge
import java.lang.ref.WeakReference

internal open class CircleImpl(
    private val controller: WeakReference<PlatformBridge>,
    private val id: String
) : Circle {

    var onClick: CircleClickCallback? = null

    override fun destroy() {
        controller.get()?.destroyCircle(id)
    }

    override fun setOnClickListener(listener: CircleClickCallback?) {
        onClick = listener
    }
}