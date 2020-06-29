package ru.dublgis.dgismobile.sdktestapp.menu

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.TextView
import ru.dublgis.dgismobile.sdktestapp.R

class ItemButton(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    constructor(context: Context) : this(context, null)

    init {
        inflate(context, R.layout.layout_item_button, this)

        val itemName: TextView = findViewById(R.id.itemName)
    }
}