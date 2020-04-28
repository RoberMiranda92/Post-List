package com.robertomiranda.data

import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post
import com.robertomiranda.data.models.User
import io.reactivex.Flowable
import io.reactivex.Maybe

interface IRepository {

    fun getAllPost(): Flowable<List<Post>>

    fun getPostById(id: Int): Maybe<Post>

    fun getAllCommentsFromPost(postId: Int): Flowable<List<Comment>>
}