package ru.dublgis.dgismobile.sdktestapp.menu

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.layout_item_button.view.*
import ru.dublgis.dgismobile.sdktestapp.MapActivity
import ru.dublgis.dgismobile.sdktestapp.R
import kotlin.reflect.KClass

class ItemButton(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs) {

    private lateinit var nextActivityClass: KClass<out MapActivity>
    private lateinit var text: String

    constructor(context: Context, text: String, nextActivityClass: KClass<out MapActivity>) : this(
        context,
        null
    ) {
        this.text = text
        this.nextActivityClass = nextActivityClass
        itemName.text = text
    }

    init {
        inflate(context, R.layout.layout_item_button, this)

        setOnClickListener {
            MapActivity.startActivity(context, text, nextActivityClass)
        }
    }
}