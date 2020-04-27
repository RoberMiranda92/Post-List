package com.robertomiranda.data.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class CommentRoom(

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "postId")
    val postId: Int,

    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "body")
    val body: String,

    @ColumnInfo(name = "email")
    val email: String
)
