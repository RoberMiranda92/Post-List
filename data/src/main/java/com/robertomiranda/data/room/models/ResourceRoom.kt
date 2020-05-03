package com.robertomiranda.data.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "resources")
data class ResourceRoom(
    @ColumnInfo(name = "key")
    val key: String,

    @ColumnInfo(name = "value")
    val value: String
)