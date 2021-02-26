# 2GIS Android MapGL

![Bintray](https://img.shields.io/bintray/v/2gis/maven/mapgl) ![Bintray](https://img.shields.io/bintray/dt/2gis/maven/mapgl)

2GIS Android MapGL is an SDK that allows you to add a [2GIS map](https://2gis.ae/) to your Android application. It can be used to display the map in your layout, add custom markers to it, and highlight various objects on the map, such as buildings, roads, and others.

This SDK uses [Android WebView](https://developer.android.com/reference/android/webkit/WebView) to render the map. If you need a more native solution (for example, if you don't want to display web content inside your app or if you need to support older versions of Android), take a look at [Android Native SDK](https://docs.2gis.com/en/android/native/maps/overview).


## Getting API Keys

Usage of this SDK requires an API key to connect to 2GIS servers and retrieve the geographical data. This API key is unique to the SDK and cannot be used with other 2GIS SDKs.

Additionally, if you plan to draw routes on the map, you will need a separate key—a [Directions API](https://docs.2gis.com/en/api/navigation/directions/overview) key—to calculate and display an optimal route.

To obtain either of these API keys, fill in the form at [dev.2gis.com](https://dev.2gis.com/order).


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


## Running Example App

To run the example app, clone this Git repository and add your API key to the [local.properties](https://developer.android.com/studio/build#properties-files) file in your project:

```
apiKey=YOUR_API_KEY
```


## Documentation

Full documentation, including [usage examples](https://docs.2gis.com/en/android/webgl/maps/examples) and [API reference](https://docs.2gis.com/en/android/webgl/maps/reference/mapObjectsByIds) with detailed descriptions of all classes and methods, can be found at [docs.2gis.com](https://docs.2gis.com/en/android/webgl/maps/overview).


## License

2GIS Android MapGL is licensed under the BSD 2-Clause "Simplified" License. See the [LICENSE](https://github.com/2gis/MapGL-Android/blob/master/LICENSE) file for more information.
