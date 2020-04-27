package com.robertomiranda.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import com.robertomiranda.data.room.models.CommentRoom
import io.reactivex.Maybe

@Dao
interface CommentsDao {

    @Query("SELECT * FROM comments")
    fun getAllComments(): Maybe<List<CommentRoom>>

    @Query("SELECT * FROM comments WHERE postId = :id")
    fun getCommentByPostId(id: Int): Maybe<List<CommentRoom>>
}