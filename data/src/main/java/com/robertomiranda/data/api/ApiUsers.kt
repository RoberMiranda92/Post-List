package com.robertomiranda.data.api

import com.robertomiranda.data.api.models.PostApi
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiUsers {

    @GET("users")
    fun getAllUsers(): Maybe<List<PostApi>>

    @GET("users/{id}")
    fun getUserById(@Path("id") id: String): Maybe<PostApi>
}