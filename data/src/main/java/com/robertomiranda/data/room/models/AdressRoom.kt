package com.robertomiranda.data.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "addresses")
data class AddressRoom(

    @PrimaryKey(autoGenerate = true)
    val id :Long,

    @ColumnInfo(name = "zipcode")
    val zipCode: String,

    @ColumnInfo(name = "geo")
    val geoId: Long,

    @ColumnInfo(name = "suite")
    val suite: String,

    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "street")
    val street: String
)