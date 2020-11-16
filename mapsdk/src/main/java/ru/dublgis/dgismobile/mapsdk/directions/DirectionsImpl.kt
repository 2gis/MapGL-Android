package ru.dublgis.dgismobile.mapsdk.directions

import android.util.JsonWriter
import androidx.annotation.RequiresApi
import ru.dublgis.dgismobile.mapsdk.IPlatformSerializable
import ru.dublgis.dgismobile.mapsdk.OnFinished
import ru.dublgis.dgismobile.mapsdk.PlatformBridge
import java.lang.IllegalStateException
import java.lang.ref.WeakReference
import java.util.concurrent.CompletableFuture


internal class DirectionsImpl(
    private val controller: WeakReference<PlatformBridge>,
    private val id: String
) : Directions, IPlatformSerializable {

    private val bridge: PlatformBridge
        get() {
            return controller.get() ?: throw IllegalStateException("Web Bridge is not available")
        }

    override fun carRoute(
        carRouteOptions: CarRouteOptions,
        onFinished: OnFinished<Unit>?
    ) {
        bridge.call("carRoute", onFinished, this, carRouteOptions)
    }

    @RequiresApi(24)
    override fun carRoute(carRouteOptions: CarRouteOptions) : CompletableFuture<Unit> {
        return bridge
            .call("carRoute", this, carRouteOptions)
    }

    override fun pedestrianRoute(
        options: PedestrianRouteOptions,
        onFinished: OnFinished<Unit>?
    ) {
        bridge
            .call("pedestrianRoute", onFinished, this, options)
    }

    @RequiresApi(24)
    override fun pedestrianRoute(options: PedestrianRouteOptions) : CompletableFuture<Unit> {
        return bridge
            .call("pedestrianRoute", this, options)
    }

    override fun clear() {
        bridge.call("clearRoutes", null, this)
    }

    override fun destroy() {
        bridge.call("destroyDirections", null, this)
    }

    override fun dump(writer: JsonWriter) {
        writer.apply {
            beginObject()
            name("directionsObjectId").value(id)
            endObject()
        }
    }
}