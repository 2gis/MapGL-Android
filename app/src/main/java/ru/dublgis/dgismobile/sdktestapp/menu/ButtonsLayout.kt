package ru.dublgis.dgismobile.sdktestapp.menu

import android.content.Context
import android.util.AttributeSet
import android.view.View.OnClickListener
import android.widget.LinearLayout
import ru.dublgis.dgismobile.sdktestapp.*

class ButtonsLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private var activeButton: MenuButton? = null
    private val onClickListener: OnClickListener = OnClickListener {
        val newButton = it as MenuButton
        if (newButton != activeButton) {
            activeButton?.unselect()
            newButton.select()
            activeButton = newButton
            return@OnClickListener
        }

        if (newButton is ExpandableGroupButton && newButton.isSelect) {
            newButton.unselect()
        } else {
            newButton.select()
        }
    }

    init {
        addButtons()
    }

    private fun addButtons() {
        addView(GroupButton(context, "Markers", MarkersActivity::class, onClickListener))
        addView(GroupButton(context, "Labels", LabelsActivity::class, onClickListener))
        addView(GroupButton(context, "Style", StyleActivity::class, onClickListener))
        addView(
            GroupButton(
                context,
                "Object selection",
                ObjectSelectionActivity::class,
                onClickListener
            )
        )
        addView(
            ExpandableGroupButton(
                context,
                "Geometries",
                getGeometriesButtons(),
                onClickListener
            )
        )

        addView(GroupButton(context, "Clustering", ClusterersActivity::class, onClickListener))

        addView(
            ExpandableGroupButton(
                context,
                "Directions",
                listOf(
                    ItemButton(context, "Car Route", DirectionsActivity::class),
                    ItemButton(context, "Pedestrian Route", PedestrianRouteActivity::class)
                ),
                onClickListener
            )
        )
    }

    private fun getGeometriesButtons(): Collection<ItemButton> {
        val itemButtonsList = mutableListOf<ItemButton>()
        with(itemButtonsList) {
            add(ItemButton(context, "Polyline", PolylineActivity::class))
            add(ItemButton(context, "Polygon", PolygonActivity::class))
            add(ItemButton(context, "Circle", CircleActivity::class))
            add(ItemButton(context, "Circle Marker", CircleMarkerActivity::class))
        }

        return itemButtonsList
    }
}

interface MenuButton {
    val isSelect: Boolean
    fun select()
    fun unselect()
}