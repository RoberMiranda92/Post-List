package com.robertomiranda.data.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.robertomiranda.data.api.models.GeoApi

@Entity(tableName = "addresses")
data class AddressRoom(

    @ColumnInfo(name = "zipcode")
    val zipcode: String? = null,

    @ColumnInfo(name = "geo")
    val geo: GeoApi? = null,

    @ColumnInfo(name = "suite")
    val suite: String? = null,

    @ColumnInfo(name = "city")
    val city: String? = null,

    @ColumnInfo(name = "street")
    val street: String? = null
)