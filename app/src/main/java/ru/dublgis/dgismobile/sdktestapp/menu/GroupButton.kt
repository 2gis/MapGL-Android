package ru.dublgis.dgismobile.sdktestapp.menu

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.layout_group_button.view.*
import ru.dublgis.dgismobile.sdktestapp.MapActivity
import ru.dublgis.dgismobile.sdktestapp.R
import kotlin.reflect.KClass

class GroupButton(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    private lateinit var nextActivityClass: KClass<out MapActivity>
    private lateinit var text: String

    constructor(
        context: Context,
        text: String,
        nextActivityClass: KClass<out MapActivity>
    ) : this(
        context,
        null
    ) {
        inflate(context, R.layout.layout_group_button, this)
        this.text = text
        this.nextActivityClass = nextActivityClass
        groupName.text = text
        setOnClickListener {
            MapActivity.startActivity(context, text, nextActivityClass)
        }
    }
}
