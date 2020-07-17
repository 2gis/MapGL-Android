package ru.dublgis.dgismobile.mapsdk.directions

/**
 * Directions initialization options.
 */
class DirectionsOptions(
    /**
     * Directions API access key.
     */
    val directionsApiKey: String
) {

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append("{")

        builder.append("directionsApiKey: \'$directionsApiKey\',")

        builder.append("}")

        return builder.toString()
    }
}