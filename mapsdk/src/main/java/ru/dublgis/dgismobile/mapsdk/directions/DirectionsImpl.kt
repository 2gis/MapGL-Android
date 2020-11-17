package ru.dublgis.dgismobile.mapsdk.directions

import android.util.JsonWriter
import androidx.annotation.RequiresApi
import ru.dublgis.dgismobile.mapsdk.PlatformSerializable
import ru.dublgis.dgismobile.mapsdk.JsArg
import ru.dublgis.dgismobile.mapsdk.OnFinished
import ru.dublgis.dgismobile.mapsdk.PlatformBridge
import java.lang.IllegalStateException
import java.lang.ref.WeakReference
import java.util.concurrent.CompletableFuture


internal class DirectionsImpl(
    private val controller: WeakReference<PlatformBridge>,
    private val id: String
) : Directions, PlatformSerializable {

    private val bridge: PlatformBridge
        get() {
            return controller.get() ?: throw IllegalStateException("Web Bridge is not available")
        }

    override fun carRoute(
        carRouteOptions: CarRouteOptions,
        onFinished: OnFinished<Unit>?
    ) {
        bridge.call("carRoute",
            args = listOf(JsArg(this), JsArg(carRouteOptions)),
            onFinished = onFinished
        )
    }

    @RequiresApi(24)
    override fun carRoute(carRouteOptions: CarRouteOptions) : CompletableFuture<Unit> {
        return bridge
            .call("carRoute", JsArg(this), JsArg(carRouteOptions))
    }

    override fun pedestrianRoute(
        options: PedestrianRouteOptions,
        onFinished: OnFinished<Unit>?
    ) {
        bridge.call("pedestrianRoute",
            args = listOf(JsArg(this), JsArg(options)),
            onFinished = onFinished)
    }

    @RequiresApi(24)
    override fun pedestrianRoute(options: PedestrianRouteOptions) : CompletableFuture<Unit> {
        return bridge.call("pedestrianRoute", JsArg(this), JsArg(options))
    }

    override fun clear() {
        bridge.call("clearRoutes",
            args = listOf(JsArg(id))
        )
    }

    override fun destroy() {
        bridge.call("destroyDirections",
            args = listOf(JsArg(id))
        )
    }

    override fun dump(writer: JsonWriter) {
        writer.apply {
            beginObject()
            name("directionsObjectId").value(id)
            endObject()
        }
    }
}