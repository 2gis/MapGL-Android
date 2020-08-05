package ru.dublgis.dgismobile.mapsdk.utils.permissions


interface PermissionHandler {
    fun onResult(
        requestCode: Int,
        grantedPermissions: Array<String>
    )
}