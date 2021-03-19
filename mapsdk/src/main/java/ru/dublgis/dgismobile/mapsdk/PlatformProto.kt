package ru.dublgis.dgismobile.mapsdk

import android.util.JsonWriter
import java.io.StringWriter
import kotlin.collections.Map


internal interface PlatformSerializable {
    fun dump(writer: JsonWriter)
}

internal fun JsonWriter.value(serializable: PlatformSerializable?) {
    if (serializable != null) {
        serializable.dump(this)
    }
    else {
        nullValue()
    }
}

internal class JsArg {
    private val repr: String

    constructor(value: String?) {
        repr = if (value != null) "\"$value\"" else "null"
    }

    constructor(value: Number) {
        repr = value.toString()
    }

    constructor(value: PlatformSerializable?) {
        if (value != null) {
            repr = with(StringWriter()) {
                JsonWriter(this).let {
                    value.dump(it)
                }
                toString()
            }
        }
        else {
            repr = "null"
        }
    }

    constructor(value: Boolean?) {
        repr = "$value"
    }

    constructor(value: Iterable<JsArg>) {
        repr = value.joinToString(
            prefix = "[",
            postfix = "]",
            separator = ","
        ) {
            it.toString()
        }
    }

    constructor(value: Map<String, JsArg>) {
        repr = value.entries.joinToString(
            prefix = "{",
            postfix = "}",
            separator = ","
        ) {
            "\"${it.key}\": ${it.value}"
        }
    }

    override fun toString(): String = repr
}


