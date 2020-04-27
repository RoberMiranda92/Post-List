package com.robertomiranda.data.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class CommentRoom(

    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "postId")
    val postId: Int? = null,

    @PrimaryKey
    val id: Int? = null,

    @ColumnInfo(name = "body")
    val body: String? = null,

    @ColumnInfo(name = "email")
    val email: String? = null
)
