# MapGL Android API

## Introduction

MapGL Android API is a wrapper around [MapGL API](/en/mapgl/overview) that allows you to add a [2GIS map](https://2gis.ae/) to your Android application.

Like the regular [Android SDK](/en/android/sdk/overview), it can be used to display the map in your interface and add various objects to it, but unlike Android SDK, MapGL Android API uses [WebView](https://developer.android.com/reference/android/webkit/WebView) to render the map and is more limited in capabilities.

## Getting an access key

Usage of this SDK requires an `API key` to connect to 2GIS servers and retrieve the geographical data. This `API key` is unique to the SDK and cannot be used with other 2GIS SDKs.

Additionally, if you plan to draw routes on the map, you will need a separate key - a [Directions API](/en/api/navigation/directions/overview) key - to calculate and display an optimal route.

To obtain either of these API keys, fill in the form at [dev.2gis.com](https://dev.2gis.com/order).

## Installation

To install the SDK:

1. Declare a custom repository in your build.gradle file:

```
repositories {
    maven {
     url "https://artifactory.2gis.dev/sdk-maven-release"
    }
}
```

2. Add a build dependency:

```
dependencies {
    implementation 'ru.dublgis.dgismobile.mapsdk:mapsdk:latest.release'
}
```

After that, you should be good to go. Check the [Examples](/en/android/mapgl/maps/examples) section to see how to display the map in your application. Alternatively, check the [API Reference](/en/android/mapgl/maps/reference) to learn more about specific classes and methods.

Also, you could visit [the GitHub repository](https://github.com/2gis/MapGL-Android/) to learn more about the SDK and get familiar with sample project.

## License

MapGL Android API is licensed under the BSD 2-Clause "Simplified" License. See the [LICENSE](https://github.com/2gis/MapGL-Android/blob/master/LICENSE) file for more information.
