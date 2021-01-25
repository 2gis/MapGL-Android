# Examples

## Creating a map widget

To display a map, first add the following Fragment to your layout:

```
<fragment
    android:name="ru.dublgis.dgismobile.mapsdk.MapFragment"
    android:id="@+id/mapFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
/>
```

Then, initialize the widget by calling the `setup()` method and passing your `API key`. You can also pass the initial coordinates and the required zoom level. See the [API Reference](/en/android/webgl/maps/reference/MapFragment) for the full list of options.

For example, the following code will show the map of Moscow centered around the Kremlin with the default level of zoom:

```
val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as MapFragment
mapFragment.setup(
    apiKey = "Your API key",
    center = LonLat(37.6175, 55.7520),
    zoom = 16.0
)
```

<img src="/img/android_mapgl_examples_kremlin.png" alt="" /> <br/>

To call a custom function after the map has been initialized, you can specify it as a callback:

```
mapFragment.mapReadyCallback = this::onDGisMapReady
```

## Adding a marker

You can add any number of markers to a map. The most common way to do that is in the callback function of the map:

```
private fun onDGisMapReady(map: DGisMap?) {
    map?.let {
        val marker = it.addMarker(MarkerOptions(
            LonLat(37.6175, 55.7520)
        )
    }
}
...
mapFragment.mapReadyCallback = this::onDGisMapReady
```

<img src="/img/android_mapgl_examples_kremlin_with_marker.png" alt="" /> <br/>

The only required parameter is the coordinates of the marker.

Additionally, you can change the marker's appearance. You can specify the icon in SVG format, the size of the marker in pixels (width × height), and the anchor - the coordinates of the hotspot of the icon (X × Y, relative to the top-left corner). See the [API Reference](/en/android/webgl/maps/reference/MarkerOptions) for more information on the MarkerOptions class.

<img src="/img/android_mapgl_examples_anchor.png" alt="" /> <br/>

```
private fun onDGisMapReady(map: DGisMap?) {
    map?.let {
        val marker = it.addMarker(MarkerOptions(
            LonLat(37.6175, 55.7520),
            icon = iconFromSvgAsset(assets, "pin.svg"),
            size = 30.0 to 48.0,
            anchor = 15.0 to 48.0)
        )
    }
}
```

You can also add a click listener to the marker to receive a click/tap event:

```
private fun onDGisMapReady(map: DGisMap?) {
    map?.let {
        val marker = it.addMarker(MarkerOptions(
            LonLat(37.6175, 55.7520),
            icon = iconFromSvgAsset(assets, "pin.svg"),
            size = 30.0 to 48.0,
            anchor = 15.0 to 48.0)
        )
        marker.setOnClickListener {
            Toast.makeText(this, "Marker tap", Toast.LENGTH_LONG).show()
        }
    }
}
```

## Handling touch events

To receive map tap events, you can add a click listener to the map itself.

For each object on a map, you can get the coordinates (`event.lngLat`) and internal ID (`event.target.id`). You can then use the ID of an object to highlight that object on the map (see [Highlighting objects](#nav-lvl1--Highlighting_objects)). The same ID can be used, for example, to get full information about the object via the [Places API](/en/api/search/places/overview), since the IDs are the same for all APIs.

```
fun onDGisMapReady(map: Map?) {
    map?.setOnClickListener { event : MapPointerEvent ->
        val coordinates = "${event.lngLat.lat}° N, ${event.lngLat.lon}° E";
        Toast.makeText(this, coordinates.toString(), Toast.LENGTH_LONG).show();
        var objectId = event.target.id;
        Toast.makeText(this, objectId.toString(), Toast.LENGTH_LONG).show();
    }
}
```

## Highlighting objects

You can highlight map objects, such as buildings, roads, and others.

To do that, call the [setSelectedObjects()](/en/android/webgl/maps/reference/Map#nav-lvl2--setSelectedObjects) method and pass the list of object IDs wrapped in a call of [mapObjectsByIds()](/en/android/webgl/maps/reference/mapObjectsByIds). You can get the IDs by adding a click listener to the map (see the [Handling touch events section](#nav-lvl1--Handling_touch_events)).

```
map?.setSelectedObjects(mapObjectsByIds("48231504731808815", "23520539192555249"))
```

<img src="/img/android_mapgl_examples_highlight.gif" alt="" /> <br/>

To change the list of highlighted objects, simply call this method again, passing the list of new IDs.

To disable highlighting, pass an empty list to the [setSelectedObjects()](/en/android/webgl/maps/reference/Map#nav-lvl2--setSelectedObjects) method.

```
map?.setSelectedObjects(listOf())
```
