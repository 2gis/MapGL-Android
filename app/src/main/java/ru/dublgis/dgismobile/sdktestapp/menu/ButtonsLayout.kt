package ru.dublgis.dgismobile.sdktestapp.menu

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import ru.dublgis.dgismobile.sdktestapp.*

class ButtonsLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    init {
        addButtons()
    }

    private fun addButtons() {
        addView(GroupButton(context, "Markers", MarkersActivity::class))
        addView(GroupButton(context, "Labels", LabelsActivity::class))
        addView(GroupButton(context, "Style", StyleActivity::class))
        addView(GroupButton(context, "Object selection", ObjectSelectionActivity::class))
        addView(GroupButton(context, "Clustering", ClusterersActivity::class))

        addView(
            ExpandableGroupButton(
                context,
                "Geometries",
                listOf(
                    ItemButton(context, "Polyline", PolylineActivity::class),
                    ItemButton(context, "Polygon", PolygonActivity::class),
                    ItemButton(context, "Circle", CircleActivity::class),
                    ItemButton(context, "Circle Marker", CircleMarkerActivity::class)
                )
            )
        )

        addView(
            ExpandableGroupButton(
                context,
                "Directions",
                listOf(
                    ItemButton(context, "Car Route", DirectionsActivity::class),
                    ItemButton(context, "Pedestrian Route", PedestrianRouteActivity::class)
                )
            )
        )
    }
}
