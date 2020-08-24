package ru.dublgis.dgismobile.mapsdk.labels

import ru.dublgis.dgismobile.mapsdk.utils.ImageDescriptor
import ru.dublgis.dgismobile.mapsdk.utils.ImageDescriptorImpl
import ru.dublgis.dgismobile.mapsdk.utils.image.Image


typealias Size = Pair<Int, Int>

/**
 * Source image for text label background.
 */
class LabelImage(
    /**
     * Source image URL.
     */
    var image: Image? = null,
    /**
     * [width, height] — image size in logical pixels
     */
    val size: Size,
    /**
     * Defines the parts of the image that can be stretched horizontally.
     */
    val stretchX: Collection<Size>,
    /**
     * Defines the parts of the image that can be stretched vertically.
     */
    val stretchY: Collection<Size>,
    /**
     * Sets the space in pixels between the label text box and the edge
     * of the stretched image for all four sides [top, right, bottom, left],
     * like in CSS. [0, 0, 0, 0] by default.
     */
    val padding: Collection<Int>,

    /**
     * The ratio of logical pixels in the image to physical pixels on the screen.
     */
    val pixelRatio: Float? = null
) {

    private var imageDescriptor: ImageDescriptor? = null

    @Deprecated("This constructor is deprecated. Use primary constructor instead.")
    constructor(
        /**
         * Source image URL.
         */
        image: ImageDescriptor,
        /**
         * [width, height] — image size in logical pixels
         */
        size: Size,
        /**
         * Defines the parts of the image that can be stretched horizontally.
         */
        stretchX: Collection<Size>,
        /**
         * Defines the parts of the image that can be stretched vertically.
         */
        stretchY: Collection<Size>,
        /**
         * Sets the space in pixels between the label text box and the edge
         * of the stretched image for all four sides [top, right, bottom, left],
         * like in CSS. [0, 0, 0, 0] by default.
         */
        padding: Collection<Int>,

        /**
         * The ratio of logical pixels in the image to physical pixels on the screen.
         */
        pixelRatio: Float? = null
    ) : this(null, size, stretchX, stretchY, padding, pixelRatio) {
        this.imageDescriptor = image
    }

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append("{")

        val stretchX_ = stretchX.joinToString(
            separator = ",",
            prefix = "[",
            postfix = ",]",
            transform = {
                "[${it.first}, ${it.second}]"
            }
        )

        val stretchY_ = stretchY.joinToString(
            separator = ",",
            prefix = "[",
            postfix = ",]",
            transform = {
                "[${it.first}, ${it.second}]"
            }
        )

        builder.append("url: '${getImageJsFormat()}',")
        builder.append(" size: [${size.first}, ${size.second}],")
        builder.append(" stretchX: $stretchX_,")
        builder.append(" stretchY: $stretchY_,")
        builder.append(" padding: $padding,")
        if (pixelRatio != null) builder.append(" pixelRatio: $pixelRatio,")

        builder.append("}")

        return builder.toString()
    }

    private fun getImageJsFormat(): String? {
        image?.let { return it.toJsFormat() }
        imageDescriptor?.let { return (it as ImageDescriptorImpl).toJsFormat() }

        return null
    }
}