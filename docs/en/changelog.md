# Release notes

## v1.7.0

**Release Date:** 18.11.2020

* Added [`pedestrianRoute`](/en/android/webgl/maps/reference/Directions#nav-lvl2--pedestrianRoute) method in [`Directions`](/en/android/webgl/maps/reference/Directions) that calculate and show route on map for pedestrians.

## 1.6.0

**Release Date:** 16.10.2020

* Added [`autoHideOSMCopyright`](/en/android/webgl/maps/reference/Map#nav-lvl2--autoHideOSMCopyright) in [`Map`](/en/android/webgl/maps/reference/Map) option. If true, the OSM copyright will be hidden after 5 seconds from the map initialization.

## 1.5.0

**Release Date:** 13.10.2020

* Added [`disableRotationByUserInteraction`](/en/android/webgl/maps/reference/Map#nav-lvl2--disableRotationByUserInteraction), [`disablePitchByUserInteraction`](/en/android/webgl/maps/reference/Map#nav-lvl2--disablePitchByUserInteraction) [`Map`](/en/android/webgl/maps/reference/Map) properties
* Added [`zIndex`](en/android/webgl/maps/reference/MarkerOptions#nav-lvl2--zIndex)  for [`MarkerOptions`](/en/android/webgl/maps/reference/MarkerOptions)

## 1.4.0

**Release Date:** 01.10.2020

* Added parameters [`offset`](/en/android/webgl/maps/reference/LabelOptions#nav-lvl2--offset) and [`relativeAnchor`](/en/android/webgl/maps/reference/LabelOptions#nav-lvl2--relativeAnchor) in [`LabelOptions`](en/android/webgl/maps/reference/LabelOptions) for positioning marker's label

## 1.3.0

**Release Date:** 21.09.2020

* Added **Map**.[`bounds`](/en/android/webgl/maps/reference/Map#nav-lvl2--bounds) that returns geographical bounds visible in the current map view.
* Added [`ImageFactory`](/en/android/webgl/maps/reference/ImageFactory) for loading icon image from assets, resources, Bitmap or local file
* Added _onFinish_ callback for **Directions**.[`carRoute`](/en/android/webgl/maps/reference/Directions#nav-lvl2--carRoute) that allow get successful or failed result on function call
* Added stretched image background [`LabelImage`](/en/android/webgl/maps/reference/LabelImage) that can be used in [`LabelOptions`](en/android/webgl/maps/reference/LabelOptions)
* Added method for showing user geolocation – [`enableUserLocation`](https://docs-canary.2gis.com/en/android/webgl/maps/reference/Map#nav-lvl2--enableUserLocation) [`disableUserLocation`](/en/android/webgl/maps/reference/Map#nav-lvl2--disableUserLocation)

## 1.2.1

**Release Date:** 25.07.2020

* Geometries can be drawn over the map
* Added marker's label [`customization`](/en/android/webgl/maps/reference/LabelOptions)
* Added [`Directions`](/en/android/webgl/maps/reference/Directions)
* Added [`Clusterer`](/en/android/webgl/maps/reference/Clusterer)
