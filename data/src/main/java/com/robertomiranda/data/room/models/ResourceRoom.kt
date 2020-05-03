package com.robertomiranda.data.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "resources")
data class ResourceRoom(

    @PrimaryKey
    @ColumnInfo(name = "resourceKey")
    val key: String,

    @ColumnInfo(name = "value")
    val value: String
)