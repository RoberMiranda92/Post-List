package com.robertomiranda.data

import com.robertomiranda.data.api.models.PostApi
import io.reactivex.Maybe

interface IRepository {

    fun getAllPost(): Maybe<List<Post>>

    fun getPostById(id: String)

    fun getAllCommentsFromPost(postId: String)

    fun getUserById(id: String)

    fun getAllPostFromUser(userID: String)

    fun getAllCommentsFromUser(userID: String)
}