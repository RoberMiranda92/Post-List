package com.robertomiranda.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.robertomiranda.data.room.models.CommentRoom
import io.reactivex.Flowable
import io.reactivex.Maybe
@Dao
interface CommentsDao {

    @Query("SELECT * FROM comments")
    fun getAllComments(): Flowable<List<CommentRoom>>

    @Query("SELECT * FROM comments WHERE postId = :id")
    fun getCommentByPostId(id: Int): Flowable<List<CommentRoom>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCommentsList(commentsList: MutableList<CommentRoom>): Maybe<List<Long>>
}