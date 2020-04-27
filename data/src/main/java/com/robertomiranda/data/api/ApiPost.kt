package com.robertomiranda.data.api

import com.robertomiranda.data.api.models.PostApi
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiPost {

    @GET("post")
    fun getAllPost(): Maybe<List<PostApi>>

    @GET("post/{id}")
    fun getPostById(@Path("id") id: Int): Maybe<PostApi>

}