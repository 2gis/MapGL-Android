package ru.dublgis.dgismobile.mapsdk.location

class UserLocationOptions(
    val isVisible: Boolean = true,
    /**
     * Desired interval for active location updates, in milliseconds.
     */
    val interval: Long = 10000,
    /**
     *  The fastest interval for location updates, in milliseconds.
     */
    val fastestInterval: Long = 5000,
    /**
     * An accuracy or power constant.
     */
    val priority: Priority = Priority.HIGH_ACCURACY
)

enum class Priority(val value: Int) {
    HIGH_ACCURACY(100),
    BALANCED_POWER_ACCURACY(102),
    LOW_POWER(104),
    NO_POWER(105)
}