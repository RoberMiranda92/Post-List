package com.robertomiranda.data.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostRoom(

    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "userId")
    val userId: Int? = null,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "body")
    val body: String? = null,

    @ColumnInfo(name = "email")
    val email: String? = null
)
