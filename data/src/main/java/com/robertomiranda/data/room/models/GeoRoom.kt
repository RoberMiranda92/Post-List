package com.robertomiranda.data.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "geo")
data class GeoRoom(

    @ColumnInfo(name = "lng")
    val lng: String? = null,

    @ColumnInfo(name = "lat")
    val lat: String? = null
)

