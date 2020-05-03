package com.robertomiranda.data.repository.local

import androidx.paging.PagedList
import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Post
import com.robertomiranda.data.models.Resource
import com.robertomiranda.data.models.User
import com.robertomiranda.data.repository.IRepository
import io.reactivex.Flowable
import io.reactivex.Maybe

interface ILocalRepository : IRepository {

    fun getAllPostPaginated(): Flowable<PagedList<Post>>

    fun addAllPost(postList: List<Post>): Maybe<List<Long>>

    fun addAllComments(commentList: List<Comment>): Maybe<List<Long>>

    fun addAllUsers(userList: List<User>): Maybe<List<Long>>

    fun addAllResources(resourceList:List<Resource>): Maybe<List<Long>>

    fun getResourceFromEmail(email:String): Maybe<Resource>
}