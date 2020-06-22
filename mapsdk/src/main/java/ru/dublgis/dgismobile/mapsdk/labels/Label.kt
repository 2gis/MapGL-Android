package ru.dublgis.dgismobile.mapsdk.labels

interface Label {

    fun isHidden(): Boolean

    fun hide()

    fun show()

    /**
     * Destroys the label.
     */
    fun destroy()
}