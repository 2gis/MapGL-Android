package ru.dublgis.dgismobile.mapsdk.utils.location

import ru.dublgis.dgismobile.mapsdk.utils.PermissionOptions

class UserLocationOptions(
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
    val priority: Int? = null,
    /**
     *  The permission options for location determination.
     */
    val permissionOptions: PermissionOptions
)