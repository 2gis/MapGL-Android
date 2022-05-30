# Release notes

## 1.9.0

**Release Date:** 27.05.2021

* Added `Map.fitBounds` that moves the map so that the specified area becomes visible.
* Added `ImageFactory.fromUrl` for creating `Image` from HTTP URL.
* Added `LonLatBounds.extend` that extend the bounds to include given points.
* Added `Map.floorPlan` for getting information about the current floor plan.
* Added `Map.padding` that is taken into account when positioning the map.
* Added `Map.setStyleState, Map.patchStyleState` for specifying style-specific variables.
* Added `Map.isSupported, Map.notSupportedReason` to check if the device supports the SDK.
* Added `Map.language` for specifying the map language.
* The 2GIS site opens when clicking the copyright.
* Removed deprecated `MarkerIconDescriptor`. Use `Image` instead.

## 1.8.0

**Release Date:** 22.12.2020

* Added **Map**.[`setStyle`](/ru/android/webgl/maps/reference/Map#nav-lvl1--setStyle) method that allowed set custom map style ID, that you can get at <https://styles.2gis.com>.
* Added **Map**.[`styleZoom`](/ru/android/webgl/maps/reference/Map#nav-lvl1--styleZoom) properties
* Added **MapFragment**.[`setup`](/ru/android/webgl/maps/reference/MapFragment#nav-lvl1--setup) arguments style, styleZoom, defaultBackgroundColor, maxBounds

## 1.7.0

**Release Date:** 18.11.2020

* Added [`pedestrianRoute`](/ru/android/webgl/maps/reference/Directions#nav-lvl2--pedestrianRoute) method in [`Directions`](/ru/android/webgl/maps/reference/Directions) that calculate and show route on map for pedestrians.

## 1.6.0

**Release Date:** 16.10.2020

* Added [`autoHideOSMCopyright`](/ru/android/webgl/maps/reference/Map#nav-lvl2--autoHideOSMCopyright) [`Map`](/ru/android/webgl/maps/reference/Map) option. If true, the OSM copyright will be hidden after 5 seconds from the map initialization.

## 1.5.0

**Release Date:** 13.10.2020

* Added [`disableRotationByUserInteraction`](/ru/android/webgl/maps/reference/Map#nav-lvl2--disableRotationByUserInteraction), [`disablePitchByUserInteraction`](/ru/android/webgl/maps/reference/Map#nav-lvl2--disablePitchByUserInteraction) [`Map`](/ru/android/webgl/maps/reference/Map) properties
* Added [`zIndex`](/ru/android/webgl/maps/reference/MarkerOptions#nav-lvl2--zIndex)  for [`MarkerOptions`](/ru/android/webgl/maps/reference/MarkerOptions)

## 1.4.0

**Release Date:** 01.10.2020

* Added parameters [`offset`](/ru/android/webgl/maps/reference/LabelOptions#nav-lvl2--offset) and [`relativeAnchor`](/ru/android/webgl/maps/reference/LabelOptions#nav-lvl2--relativeAnchor) in [`LabelOptions`](/ru/android/webgl/maps/reference/LabelOptions) for positioning marker's label

## 1.3.0

**Release Date:** 21.09.2020

* Added **Map**.[`bounds`](/ru/android/webgl/maps/reference/Map#nav-lvl2--bounds) that returns geographical bounds visible in the current map view.
* Added [`ImageFactory`](/ru/android/webgl/maps/reference/ImageFactory) for loading icon image from assets, resources, Bitmap or local file
* Added _onFinish_ callback for **Directions**.[`carRoute`](/ru/android/webgl/maps/reference/Directions#nav-lvl2--carRoute) that allow get successful or failed result on function call
* Added stretched image background [`LabelImage`](/ru/android/webgl/maps/reference/LabelImage) that can be used in [`LabelOptions`](/ru/android/webgl/maps/reference/LabelOptions)
* Added method for showing user geolocation – [`enableUserLocation`](https://docs-canary.2gis.com/ru/android/webgl/maps/reference/Map#nav-lvl2--enableUserLocation) [`disableUserLocation`](/ru/android/webgl/maps/reference/Map#nav-lvl2--disableUserLocation)

## 1.2.1

**Release Date:** 25.07.2020

* Geometries can be drawn over the map
* Added marker's label [`customization`](/ru/android/webgl/maps/reference/LabelOptions)
* Added [`Directions`](/ru/android/webgl/maps/reference/Directions)
* Added [`Clusterer`](/ru/android/webgl/maps/reference/Clusterer)
