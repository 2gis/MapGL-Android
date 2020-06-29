package ru.dublgis.dgismobile.sdktestapp.menu

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.layout_group_button.view.*
import ru.dublgis.dgismobile.sdktestapp.R

class GroupButton(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    constructor(context: Context, text: String) : this(context, null) {
        groupName.text = text
    }

    init {
        inflate(context, R.layout.layout_group_button, this)
    }
}