package ru.dublgis.dgismobile.mapsdk

import android.graphics.Color
import junit.framework.Assert.assertEquals
import org.junit.Test
import ru.dublgis.dgismobile.mapsdk.utils.ColorUtils

class ColorUnitTest {
    @Test
    fun jsColorFormat_isCorrect() {
        assertEquals("#000000ff", ColorUtils.jsColorFormat(Color.BLACK))
        assertEquals("#444444ff", ColorUtils.jsColorFormat(Color.DKGRAY))
        assertEquals("#888888ff", ColorUtils.jsColorFormat(Color.GRAY))
        assertEquals("#ccccccff", ColorUtils.jsColorFormat(Color.LTGRAY))
        assertEquals("#ffffffff", ColorUtils.jsColorFormat(Color.WHITE))
        assertEquals("#ff0000ff", ColorUtils.jsColorFormat(Color.RED))
        assertEquals("#00ff00ff", ColorUtils.jsColorFormat(Color.GREEN))
        assertEquals("#0000ffff", ColorUtils.jsColorFormat(Color.BLUE))
        assertEquals("#ffff00ff", ColorUtils.jsColorFormat(Color.YELLOW))
        assertEquals("#00ffffff", ColorUtils.jsColorFormat(Color.CYAN))
        assertEquals("#ff00ffff", ColorUtils.jsColorFormat(Color.MAGENTA))
        assertEquals("#ff00ffff", ColorUtils.jsColorFormat(Color.MAGENTA))
        assertEquals("#00000000", ColorUtils.jsColorFormat(Color.TRANSPARENT))
    }
}