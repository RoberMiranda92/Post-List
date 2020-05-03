package com.robertomiranda.data.repository.remote

import com.robertomiranda.data.models.Comment
import com.robertomiranda.data.models.Resource
import com.robertomiranda.data.models.User
import com.robertomiranda.data.repository.IRepository
import io.reactivex.Flowable

interface IRemoteRepository : IRepository {

    fun geAllUsers(): Flowable<List<User>>

    fun getAllComments(): Flowable<List<Comment>>
}