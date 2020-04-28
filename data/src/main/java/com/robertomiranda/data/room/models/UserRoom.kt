package com.robertomiranda.data.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserRoom(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "website")
    val website: String,

    @ColumnInfo(name = "address")
    val addressId: Int,

    @ColumnInfo(name = "phone")
    val phone: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "company")
    val companyId: Int,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "username")
    val username: String
)