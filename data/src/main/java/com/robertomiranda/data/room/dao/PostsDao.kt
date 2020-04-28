package com.robertomiranda.data.room.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.robertomiranda.data.room.models.PostRoom
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface PostsDao {

    @Query("SELECT * FROM posts")
    fun getAllPost(): DataSource.Factory<Int, PostRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPostList(posts: List<PostRoom>): Completable

    @Query("SELECT * FROM posts WHERE id = :id")
    fun getPostById(id: Int): Maybe<PostRoom>
}