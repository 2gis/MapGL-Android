# 2GIS Android MapGL

2GIS Android MapGL is an SDK that allows you to add a [2GIS](https://2gis.ae/) map to your Android application. It can be used to display the map in your layout, add custom markers to it, and highlight various objects on the map, such as buildings, roads, and others.

This SDK uses [Android WebView](https://developer.android.com/reference/android/webkit/WebView) to render the map. If you need a more native solution (for example, if you don't want to display web content inside your app or if you need to support older versions of Android), take a look at [Android Native SDK](https://docs.2gis.com/en/android/native/maps/overview).

Full documentation, including more usage examples and detailed descriptions of all classes and methods, can be found at [https://docs.2gis.com/en/android/webgl/maps/overview](https://docs.2gis.com/en/android/webgl/maps/overview).


## Getting an access key

Usage of this SDK requires an API key to connect to 2GIS servers and retrieve the geographical data. This API key is unique to the SDK and cannot be used with other 2GIS SDKs.

To obtain the key, contact us at [mapgl@2gis.com](mailto:mapgl@2gis.com).


## Installation

To install the SDK:

1. Declare a custom repository in your _build.gradle_ file:

```gradle
repositories {
    maven {
        url "https://dl.bintray.com/2gis/maven"
    }
}
```

2. Add a build dependency:

```gradle
dependencies {
    implementation 'ru.dublgis.dgismobile.mapsdk:mapsdk:latest.release'
}
```


## Usage

To run the example app, first add your API key to the [local.properties](https://developer.android.com/studio/build#properties-files) file in your project:

```
apiKey=YOUR_API_KEY
```

You can find more usage examples at [https://docs.2gis.com/en/android/webgl/maps/examples](https://docs.2gis.com/en/android/webgl/maps/examples).


### Creating a map widget

To display a map, first add the following Fragment to your layout:

```xml
<fragment
    android:name="ru.dublgis.dgismobile.mapsdk.MapFragment"
    android:id="@+id/mapFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
/>
```

Then, initialize the widget by calling the _setup()_ method and passing your API key. You can also pass the initial coordinates and the required zoom level. See the [API Reference](https://docs.2gis.com/en/android/webgl/maps/reference/MapFragment) for the full list of options.

For example, the following code will show the map of Moscow centered around the Kremlin with the default level of zoom:

```kotlin
val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as MapFragment
mapFragment.setup(
    apiKey = "Your API key",
    center = LonLat(37.6175, 55.7520),
    zoom = 16.0
)
```

![kremlin](https://user-images.githubusercontent.com/57934605/89265464-f33e6580-d64d-11ea-89eb-b4ee20f1dbb3.png)

To call a custom function after the map has been initialized, you can specify it as a callback:

```kotlin
mapFragment.mapReadyCallback = this::onDGisMapReady
```


### Adding a marker

You can add any number of markers to a map. The most common way to do that is in the callback function of the map:

```kotlin
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

![kremlin-marker](https://user-images.githubusercontent.com/57934605/89265704-4e705800-d64e-11ea-9c9e-1db831dcf34e.png)

The only required parameter is the coordinates of the marker.

Additionally, you can change the marker's appearance. You can specify the _icon_ in SVG format, the _size_ of the marker in pixels (width × height), and the _anchor_ - the coordinates of the hotspot of the icon (X × Y, relative to the top-left corner). See the [API Reference](https://docs.2gis.com/en/android/webgl/maps/reference/MarkerOptions) for more information on the MarkerOptions class.

![anchor](https://user-images.githubusercontent.com/57934605/89265659-40223c00-d64e-11ea-9b66-4525dfb94329.png)

```kotlin  
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

```kotlin
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


### Getting information about the tapped object

To receive map tap events, you can add a click listener to the map itself.

For each object on a map, you can get the coordinates (_event.lngLat_) and internal ID (_event.target.id_). You can then use the ID of an object to highlight that object on the map (see [Highlighting objects](#highlighting-objects)). The same ID can be used, for example, to get full information about the object via the [Places API](https://docs.2gis.com/en/api/search/places/overview), since the IDs are the same for all APIs.

```kotlin
fun onDGisMapReady(map: Map?) {
    map?.setOnClickListener { event : MapPointerEvent ->
        val coordinates = "${event.lngLat.lat}° N, ${event.lngLat.lon}° E";
        Toast.makeText(this, coordinates.toString(), Toast.LENGTH_LONG).show();
        var objectId = event.target.id;
        Toast.makeText(this, objectId.toString(), Toast.LENGTH_LONG).show();
    }
}
```


### Highlighting objects

You can highlight map objects, such as buildings, roads, and others.

To do that, call the _setSelectedObjects()_ method and pass the list of object IDs wrapped in a call of _mapObjectsByIds()_. You can get the IDs by adding a click listener to the map (see the [Getting information about the tapped object](#getting-information-about-the-tapped-object) section).

```kotlin
map?.setSelectedObjects(mapObjectsByIds("48231504731808815", "23520539192555249"))
```

![highlight](https://user-images.githubusercontent.com/57934605/89265712-53cda280-d64e-11ea-98af-763d12105f96.gif)

To change the list of highlighted objects, simply call this method again, passing the list of new IDs.

To disable highlighting, pass an empty list to the _setSelectedObjects()_ method.

```kotlin
map?.setSelectedObjects(listOf())
```


## License

2GIS Android MapGL is licensed under the BSD 2-Clause "Simplified" License. See the LICENSE file for more information.
