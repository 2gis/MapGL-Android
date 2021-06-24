# 2GIS MapGL Android API

2GIS MapGL Android API is a wrapper around [MapGL API](https://docs.2gis.com/en/mapgl/overview) that allows you to add a [2GIS map](https://2gis.ae/) to your Android application.

Like the regular [Android SDK](https://docs.2gis.com/en/android/sdk/overview), it can be used to display the map in your interface and add various objects to it, but unlike Android SDK, MapGL Android API uses [WebView](https://developer.android.com/reference/android/webkit/WebView) to render the map and is more limited in capabilities.

## Getting API keys

Usage of this SDK requires an API key to connect to 2GIS servers and retrieve the geographical data. This API key is unique to the SDK and cannot be used with other 2GIS SDKs.

Additionally, if you plan to draw routes on the map, you will need a separate key - a [Directions API](https://docs.2gis.com/en/api/navigation/directions/overview) key - to calculate and display an optimal route.

To obtain either of these API keys, fill in the form at [dev.2gis.com](https://dev.2gis.com/order).

## Installation

To install the SDK:

1. Declare a custom repository in your _build.gradle_ file:

```gradle
repositories {
    maven {
        url "https://artifactory.2gis.dev/sdk-maven-release"
    }
}
```

2. Add a build dependency:

```gradle
dependencies {
    implementation 'ru.dublgis.dgismobile.mapsdk:mapsdk:latest.release'
}
```

## Running the demo app

To run the demo app, clone this Git repository and add your API key to the [local.properties](https://developer.android.com/studio/build#properties-files) file in your project:

```
apiKey=YOUR_API_KEY
```

## Documentation

Full documentation, including [usage examples](https://docs.2gis.com/en/android/mapgl/maps/examples) and [API reference](https://docs.2gis.com/en/android/mapgl/maps/reference/mapObjectsByIds) with detailed descriptions of all classes and methods, can be found at [docs.2gis.com](https://docs.2gis.com/en/android/mapgl/maps/overview).

## License

The demo application is licensed under the BSD 2-Clause "Simplified" License. See the [LICENSE](https://github.com/2gis/MapGL-Android/blob/master/LICENSE) file for more information.
