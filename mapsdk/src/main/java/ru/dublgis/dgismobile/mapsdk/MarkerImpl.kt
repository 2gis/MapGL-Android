package ru.dublgis.dgismobile.mapsdk

import java.lang.ref.WeakReference
import kotlin.math.absoluteValue
import kotlin.random.Random


internal class MarkerImpl(
    private val controller: WeakReference<PlatformBridge>,
    private val options: MarkerOptions
) : Marker {

    val id = "${position.lon},${position.lat}_${Random.nextInt().absoluteValue}"
    var hidden = false
    var onClick: MarkerClickCallback? = null

    override var position: LonLat
        get() = options.position
        set(value) {
            controller.get()?.setMarkerCoordinates(this, value)
            options.position = value
        }

    override val isHidden: Boolean
        get() = hidden

    override fun setOnClickListener(listener: MarkerClickCallback?) {
        onClick = listener
    }

    override fun hide() {
        controller.get()?.hideMarker(this)
    }

    override fun show() {
        controller.get()?.showMarker(this)
    }
}