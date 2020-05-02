package com.robertomiranda.data.api

import com.robertomiranda.data.api.models.PostApi
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiPost {

    @GET("posts")
    fun getAllPost(): Flowable<List<PostApi>>

    @GET("posts/{id}")
    fun getPostById(@Path("id") id: Int): Single<PostApi>

}