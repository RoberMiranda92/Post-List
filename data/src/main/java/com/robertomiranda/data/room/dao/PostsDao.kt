package com.robertomiranda.data.room.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.robertomiranda.data.room.models.PostRoom
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface PostsDao {

    @Query("SELECT * FROM posts")
    fun getAllPost(): Flowable<List<PostRoom>>

    @Query("SELECT * FROM posts")
    fun getAllPostPaginated(): DataSource.Factory<Int, PostRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPostList(posts: List<PostRoom>): Maybe<List<Long>>

    @Query("SELECT * FROM posts WHERE id = :id")
    fun getPostById(id: Int): Single<PostRoom>
}