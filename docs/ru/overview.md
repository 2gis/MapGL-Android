# Overview

## Introduction

2GIS Android MapGL is an SDK that allows you to add a [2GIS](https://2gis.ae) map to your Android application. It can be used to display the map in your layout, add custom markers to it, and highlight various objects on the map, such as buildings, roads, and others.

This SDK uses [Android WebView](https://developer.android.com/reference/android/webkit/WebView) to render the map. If you need a more native solution (for example, if you don't want to display web content inside your app or if you need to support older versions of Android), take a look at `Android Native SDK`.

## Getting an access key

Usage of this SDK requires an `API key` to connect to 2GIS servers and retrieve the geographical data. This `API key` is unique to the SDK and cannot be used with other 2GIS SDKs. To obtain the key, contact us at [mapgl@2gis.com](mailto:mapgl@2gis.com).

Additionally, if you plan to draw routes on the map, you will need a separate key - a [Directions API](/ru/api/navigation/directions/overview) key - to calculate and display an optimal route. To get it, fill the form at [partner.api.2gis.ru](https://partner.api.2gis.ru/) or contact us at [content@2gis.ru](mailto:content@2gis.ru).

## Installation

To install the SDK:

1. Declare a custom repository in your build.gradle file:

```
repositories {
    maven {
     url "https://dl.bintray.com/2gis/maven"
    }
}
```

2. Add a build dependency:

```
dependencies {
    implementation 'ru.dublgis.dgismobile.mapsdk:mapsdk:latest.release'
}
```

After that, you should be good to go. Check the [Examples](/ru/android/webgl/maps/examples) section to see how to display the map in your application. Alternatively, check the [API Reference](/ru/android/webgl/maps/reference) to learn more about specific classes and methods.

## License

2GIS Android MapGL is licensed under the BSD 2-Clause "Simplified" License. See the [LICENSE](https://github.com/2gis/MapGL-Android/blob/master/LICENSE) file for more information.
