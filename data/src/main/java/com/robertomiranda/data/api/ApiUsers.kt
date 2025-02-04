package com.robertomiranda.data.api

import com.robertomiranda.data.api.models.PostApi
import com.robertomiranda.data.api.models.UserApi
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiUsers {

    @GET("users")
    fun getAllUsers(): Flowable<List<UserApi>>

    @GET("users/{id}")
    fun getUserById(@Path("id") id: Int): Single<UserApi>
}