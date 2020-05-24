package ru.dublgis.dgismobile.mapsdk


interface MapObject {
    val id: String
}


internal data class MapObjectImpl (
    override val id: String
) : MapObject


fun mapObjectById(id: String) : MapObject {
    if (id.isEmpty()) {
        throw IllegalArgumentException("map object id could not be empty string");
    }
    return MapObjectImpl(id);
}


fun mapObjectsByIds(vararg ids: String) : List<MapObject> {
    return ids.map(::mapObjectById)
}
