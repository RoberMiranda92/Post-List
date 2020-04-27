package com.robertomiranda.data.room.dao

import androidx.room.Query
import com.robertomiranda.data.room.models.CommentRoom
import io.reactivex.Maybe

interface CommentsDao {

    @Query("SELECT * FROM comments")
    fun getAllComments(): Maybe<List<CommentRoom>>

    @Query("SELECT * FROM comments WHERE postId = :id")
    fun getCommentByPostIId(id: String): Maybe<CommentRoom>
}