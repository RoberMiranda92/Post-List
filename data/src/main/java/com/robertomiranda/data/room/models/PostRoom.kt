package com.robertomiranda.data.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostRoom(

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "userId")
    val userId: Int,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "body")
    val body: String,

    @ColumnInfo(name = "email")
    val email: String? = null
)
