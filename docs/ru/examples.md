# Примеры

## Создание виджета карты

Чтобы отобразить карту, для начала добавьте следующий [Fragment](https://developer.android.com/reference/android/app/Fragment) в ваш layout-файл:

```
<fragment
    android:name="ru.dublgis.dgismobile.mapsdk.MapFragment"
    android:id="@+id/mapFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
/>
```

Затем инициализируйте виджет, вызвав метод `setup()` и передав свой ключ API. Вы также можете передать начальные координаты и требуемый масштаб. Полный список возможных параметров приведён в [описании API](/en/android/mapgl/maps/reference/MapFragment).

Например, приведённый ниже фрагмент кода показывает карту Москвы с Кремлём в центре карты и масштабом по умолчанию:

```
val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as MapFragment
mapFragment.setup(
    apiKey = "Your API key",
    center = LonLat(37.6175, 55.7520),
    zoom = 16.0
)
```

<img src="/img/android_mapgl_examples_kremlin.png" alt="" /> <br/>

Для вызова какой-либо функции после инициализации карты, укажите её как callback:

```
mapFragment.mapReadyCallback = this::onDGisMapReady
```

## Добавление маркера

Вы можете добавить на карту любое количество маркеров. Чаще всего это делается через callback-функцию карты:

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

Из параметров вам нужно задать только координаты маркера.

Кроме того, вы можете изменить внешний вид маркера. Вы можете задать иконку в SVG-формате, размер маркера в пикселях (ширина × высота), и якорь - координаты точки, к которой привязывается маркер (X × Y, относительно левого верхнего угла). Более подробную информацию о классе MarkerOptions смотрите в [описании API](/en/android/mapgl/maps/reference/MarkerOptions).

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

Также вы можете добавить click listener для маркера, чтобы получать события клика/касания:

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

## Обработка событий нажатия

Чтобы получать события нажатия на карту, вы можете добавить click listener для самой карты.

Для каждого объекта на карте можно получить координаты (`event.lngLat`) и внутренний ID (`event.target.id`). Затем вы сможете использовать ID объекта, чтобы выделить его на карте (подробнее в разделе [Выделение объектов](#nav-lvl1--Выделение_объектов)). Этот же ID можно использовать для получения полной информации об объекте через другие API, например, [Places API](/ru/api/search/places/overview), так как ID объектов одинаковы для всех API.

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

## Выделение объектов

Вы можете выделять на карте объекты: здания, дороги и т. д.

Для этого вызовите метод [mapObjectsByIds()](/en/android/mapgl/maps/reference/mapObjectsByIds), передав ему список ID объектов, а затем вызовите метод [setSelectedObjects()](/en/android/mapgl/maps/reference/Map#nav-lvl1--setSelectedObjects) по результату mapObjectsByIds(). Вы можете получить нужные ID, добавив для карты click listener (смотрите раздел [Обработка событий нажатия](#nav-lvl1--Обработка_событий_нажатия)).

```
map?.setSelectedObjects(mapObjectsByIds("48231504731808815", "23520539192555249"))
```

<img src="/img/android_mapgl_examples_highlight.gif" alt="" /> <br/>

Чтобы изменить список выделенных объектов, вызовите этот метод еще раз, передав в него новый список ID.

Чтобы убрать все выделения с карты, передайте пустой список методу [setSelectedObjects()](/en/android/mapgl/maps/reference/Map#nav-lvl2--setSelectedObjects).

```
map?.setSelectedObjects(listOf())
```
