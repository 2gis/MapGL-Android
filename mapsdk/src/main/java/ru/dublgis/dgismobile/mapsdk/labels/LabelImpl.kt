package ru.dublgis.dgismobile.mapsdk.labels

import ru.dublgis.dgismobile.mapsdk.PlatformBridge
import java.lang.ref.WeakReference

internal class LabelImpl(
    private val controller: WeakReference<PlatformBridge>,
    private val id: String
) : Label {

    private var isHidden: Boolean = false

    override fun isHidden(): Boolean {
        return isHidden
    }

    override fun hide() {
        controller.get()?.hideLabel(id)
        isHidden = true
    }

    override fun show() {
        controller.get()?.showLabel(id)
        isHidden = false
    }

    override fun destroy() {
        controller.get()?.destroyLabel(id)
    }
}