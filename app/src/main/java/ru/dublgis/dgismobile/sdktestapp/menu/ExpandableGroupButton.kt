package ru.dublgis.dgismobile.sdktestapp.menu

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.layout_exp_group_button.view.*
import kotlinx.android.synthetic.main.layout_group_button.view.*
import ru.dublgis.dgismobile.sdktestapp.R


class ExpandableGroupButton(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs), MenuButton {

    private var isSel: Boolean = false

    constructor(
        context: Context,
        text: String,
        itemButtons: Collection<ItemButton>,
        onClickListener: OnClickListener
    ) : this(
        context,
        null
    ) {
        groupName.text = text
        for (itemButton in itemButtons)
            expButtonsList.addView(itemButton)

        setOnClickListener(onClickListener)
    }

    init {
        inflate(context, R.layout.layout_exp_group_button, this)
        expButtonsList.visibility = GONE
    }

    override val isSelect: Boolean
        get() = isSel

    override fun select() {
        groupName.typeface = Typeface.create(
            resources.getString(R.string.roboto_medium),
            Typeface.NORMAL
        )
        group.setBackgroundColor(ContextCompat.getColor(context, R.color.white_gray))
        chevron.setImageDrawable(resources.getDrawable(R.drawable.ic_chevron_active))
        expButtonsList.visibility = View.VISIBLE
        isSel = true
    }

    override fun unselect() {
        groupName.typeface = Typeface.create(
            resources.getString(R.string.roboto_regular),
            Typeface.NORMAL
        )
        group.setBackgroundColor(Color.WHITE)
        chevron.setImageDrawable(resources.getDrawable(R.drawable.ic_chevron))
        expButtonsList.visibility = View.GONE
        isSel = false
    }
}