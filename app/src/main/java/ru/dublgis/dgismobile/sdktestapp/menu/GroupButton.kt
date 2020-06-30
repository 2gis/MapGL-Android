package ru.dublgis.dgismobile.sdktestapp.menu

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.layout_group_button.view.*
import ru.dublgis.dgismobile.sdktestapp.MapActivity
import ru.dublgis.dgismobile.sdktestapp.R
import kotlin.reflect.KClass

class GroupButton(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs),
    MenuButton {

    private lateinit var nextActivityClass: KClass<out MapActivity>
    private lateinit var text: String
    private var isSel: Boolean = false

    constructor(
        context: Context,
        text: String,
        nextActivityClass: KClass<out MapActivity>,
        onClickListener: OnClickListener
    ) : this(
        context,
        null
    ) {
        this.text = text
        this.nextActivityClass = nextActivityClass
        setOnClickListener(onClickListener)
        groupName.text = text
    }

    init {
        inflate(context, R.layout.layout_group_button, this)
    }

    override val isSelect: Boolean
        get() = isSel

    override fun select() {
        MapActivity.startActivity(context, text, nextActivityClass)
        chevron.setImageDrawable(resources.getDrawable(R.drawable.ic_chevron_active))
        isSel = true
    }

    override fun unselect() {
        chevron.setImageDrawable(resources.getDrawable(R.drawable.ic_chevron))
        isSel = false
    }
}