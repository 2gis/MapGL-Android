package ru.dublgis.dgismobile.sdktestapp.menu

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.layout_exp_group_button.view.*
import kotlinx.android.synthetic.main.layout_group_button.view.*
import ru.dublgis.dgismobile.sdktestapp.R

class ExpandableGroupButton(context: Context, attrs: AttributeSet?) :
    RelativeLayout(context, attrs) {

    constructor(context: Context) : this(context, null)

    init {
        inflate(context, R.layout.layout_exp_group_button, this)

        chevronRight
        groupName
        expButtonsList
    }

    fun setName(name: String) {
        groupName.setText(name)
    }

    private fun addButton(itemButton: ItemButton) {
        expButtonsList.addView(itemButton)
    }
}