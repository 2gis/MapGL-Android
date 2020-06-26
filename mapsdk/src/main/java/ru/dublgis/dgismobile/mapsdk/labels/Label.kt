package ru.dublgis.dgismobile.mapsdk.labels

/**
 * Interface for creating labels on the map.
 */
interface Label {

    /**
     * True if label is hidden
     */
    val isHidden: Boolean

    /**
     * Hides the label.
     */
    fun hide()

    /**
     * Displays hidden label.
     */
    fun show()

    /**
     * Destroys the label.
     */
    fun destroy()
}