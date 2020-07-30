package ru.dublgis.dgismobile.mapsdk.location

class UserLocationOptions(
    val isVisible: Boolean? = true,
    /**
     * Desired interval for active location updates, in milliseconds.
     */
    val interval: Long? = null,
    /**
     *  The fastest interval for location updates, in milliseconds.
     */
    val fastestInterval: Long? = null,
    /**
     * An accuracy or power constant.
     */
    val priority: Int? = null
)