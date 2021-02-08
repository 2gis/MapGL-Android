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
    LinearLayout(context, attrs) {

    private var expanded: Boolean = false

    constructor(
        context: Context,
        text: String,
        itemButtons: Collection<ItemButton>
    ) : this(
        context,
        null
    ) {
        inflate(context, R.layout.layout_exp_group_button, this)
        groupName.text = text
        for (itemButton in itemButtons)
            expButtonsList.addView(itemButton)

        setOnClickListener {
            expanded = !expanded
            updateView()
        }
        updateView()
    }

    private fun updateView() {
        groupName.setTypeface(null, if (expanded) Typeface.BOLD else Typeface.NORMAL)
        group.setBackgroundColor(if (expanded) ContextCompat.getColor(context, R.color.white_gray) else Color.WHITE)
        chevron.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_chevron_active))
        chevron.rotation = if (expanded) 0.0f else 180.0f
        expButtonsList.visibility = if (expanded) View.VISIBLE else View.GONE
        expDivider.visibility = if (expanded) View.VISIBLE else View.GONE
        divider.visibility = if (expanded) View.GONE else View.VISIBLE
    }
}