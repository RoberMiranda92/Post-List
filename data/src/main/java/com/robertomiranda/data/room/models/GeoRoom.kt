package com.robertomiranda.data.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "geo")
data class GeoRoom(

    @PrimaryKey(autoGenerate = true)
    val id :Long,

    @ColumnInfo(name = "lng")
    val lng: Long,

    @ColumnInfo(name = "lat")
    val lat: Long
)

