package com.robertomiranda.data.repository

import androidx.paging.PagedList
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post
import com.robertomiranda.data.models.Resource
import com.robertomiranda.data.models.User
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

interface IRepository {

    fun getAllPost(): Flowable<List<Post>>

    fun getPostById(id: Int): Single<Post>

    fun getAllCommentsFromPost(postId: Int): Flowable<List<Comment>>

    fun getUserById(id: Int): Single<User>

    fun getAllResources(): Flowable<List<Resource>>
}