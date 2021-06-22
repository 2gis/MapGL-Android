# 2GIS MapGL Android API

## Введение

2GIS MapGL Android API позволяет добавить [карту 2GIS](https://2gis.ru/) в ваше Android-приложение.

В отличие от [Android SDK](/ru/android/sdk/overview), MapGL Android использует [MapGL API](/ru/mapgl/overview) и [WebView](https://developer.android.com/reference/android/webkit/WebView) для отображения карты и более ограничен в возможностях.

## Получение ключа доступа

Чтобы использовать этот SDK, необходим ключ API для подключения к серверам 2GIS и получения географических данных. Этот ключ доступа API уникален для конкретного SDK и не может быть использован с другими SDK от 2GIS.

Кроме того, если вы планируете прокладывать маршруты на карте, то для вычисления и отображения оптимального маршрута вам понадобится отдельный ключ API - для [Directions API](/ru/api/navigation/directions/overview).

Чтобы получить любой из этих ключей API, заполните форму на [dev.2gis.ru](https://dev.2gis.ru/order).

## Установка

Для установки SDK:

1. Укажите пользовательский репозиторий в вашем файле build.gradle:

```
repositories {
    maven {
     url "https://artifactory.2gis.dev/sdk-maven-release"
    }
}
```

2. Добавьте зависимость:

```
dependencies {
    implementation 'ru.dublgis.dgismobile.mapsdk:mapsdk:latest.release'
}
```

После этого всё должно быть готово к работе. В разделе [Примеры](/ru/android/mapgl/maps/examples) вы можете посмотреть, как добавить карту к вашему приложению. Или загляните в [описание API](/en/android/mapgl/maps/reference), чтобы узнать больше о конкретных классах и методах.

Также доступен [GitHub-репозиторий](https://github.com/2gis/MapGL-Android/), в котором можно познакомиться с SDK и демонстрационным проектом.

## Лицензия

2GIS MapGL Android API распространяется под упрощённой лицензией BSD 2-Clause. Дополнительную информацию можно найти в файле [LICENSE](https://github.com/2gis/MapGL-Android/blob/master/LICENSE).
