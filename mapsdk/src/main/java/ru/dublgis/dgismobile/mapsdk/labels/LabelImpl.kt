package ru.dublgis.dgismobile.mapsdk.labels

import ru.dublgis.dgismobile.mapsdk.PlatformBridge
import java.lang.ref.WeakReference

internal class LabelImpl(
    private val controller: WeakReference<PlatformBridge>,
    private val id: String
) : Label {

    private var hidden = false
    override val isHidden: Boolean
        get() = hidden

    override fun hide() {
        controller.get()?.hideLabel(id)
        hidden = true
    }

    override fun show() {
        controller.get()?.showLabel(id)
        hidden = false
    }

    override fun destroy() {
        controller.get()?.destroyLabel(id)
    }
}