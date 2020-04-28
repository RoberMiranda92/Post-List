package com.robertomiranda.data

import com.robertomiranda.data.api.models.CommentApi
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post
import io.reactivex.Maybe

interface IRepository {

    fun getAllPost(): Maybe<List<Post>>

    fun getPostById(id: Int): Maybe<Post>

    fun getAllCommentsFromPost(postId: Int): Maybe<List<Comment>>

    fun getUserById(id: Int)

    fun getAllPostFromUser(userId: Int): Maybe<List<Post>>

    fun getAllCommentsFromUser(userID: Int)
}