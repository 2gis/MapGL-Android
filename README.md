# MapGL
2GIS Maps SDK for Android.

## Usage

### Get Access
As a first step to use MapGL you need to get the access key (contact us mapgl@2gis.com if you need one).

### Creating the Map
Add a <fragment> element to the activity's layout file to define a Fragment object. In this element, set the android:name attribute to "ru.dublgis.dgismobile.mapsdk.MapFragment".
```xml
<fragment
    android:name="ru.dublgis.dgismobile.mapsdk.MapFragment"
    android:id="@+id/mapFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
/>
```
You can also add a MapFragment to an Activity in code.  
The only thing we require is that you setup api key. You can initialize default map parameters like center and zoom as well
```kotlin
val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment)
    as MapFragment

mapFragment.setup(
    apiKey = "your api key here", 
    zoom = 13.0,
    center = LonLat(37.618317, 55.750574)
)
mapFragment.mapReadyCallback = this::onDGisMapReady
```
Once Map is ready mapReadyCallback will be called and Map object will be available for you

### Receiving Map Click Events
```kotlin
fun onDGisMapReady(map: Map?) {
    map?.setOnClickListener { event : MapPointerEvent ->
        // event.lngLat - Geographical coordinates
        // event.target - Target MapObject (geographical object)
    }
}
```

### Showing the Marker
The following example demonstrates how to add a marker to a map. The marker displays the string 'Hello world' in a pop-up notification when clicked. All MarkerOptions parameters except position are optional
```kotlin
private fun onDGisMapReady(map: DGisMap?) {
    map?.let {
        val marker = it.addMarker(MarkerOptions(
            LonLat(37.618317, 55.750574),
            icon = iconFromSvgAsset(assets, "pin.svg"),
            size = 30.0 to 48.0,
            anchor = 15.0 to 48.0))

        marker.setOnClickListener {
            Toast.makeText(this, "Hello world", Toast.LENGTH_LONG)
                .show()
        }
    }
}
```

### Select map objects
You can highlight objects on the map. Select them by object ids for that.
```kotlin
map?.setSelectedObjects(mapObjectsByIds("13933647002594323"))
```
Use empty list to remove any selection.
```kotlin
map?.setSelectedObjects(listOf())
```

## Example
To make the example app work you need to have the access key (contact us mapgl@2gis.com if you need one).  
At first, append your key to *local.properties* file in the root directory
`apiKey=your_api_key_here`. Then build and run project as usual.

## Installation
Setup Gradle repositories
```groovy
repositories {
	maven {
		url  "https://dl.bintray.com/2gis/maven"
	}
}
```
add dependency
```groovy
dependencies {
    implementation 'ru.dublgis.dgismobile.mapsdk:mapsdk:latest.release'
}
```

## License
MapGL is available under the BSD 2-Clause "Simplified" license. See the LICENSE file for more info.
