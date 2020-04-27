package com.robertomiranda.data.room.dao

import androidx.room.Query
import com.robertomiranda.data.room.models.PostRoom
import io.reactivex.Maybe

interface PostsDao {

    @Query("SELECT * FROM posts")
    fun getAllPost(): Maybe<List<PostRoom>>

    @Query("SELECT * FROM posts WHERE id = :id")
    fun getPostById(id: String): Maybe<PostRoom>
}