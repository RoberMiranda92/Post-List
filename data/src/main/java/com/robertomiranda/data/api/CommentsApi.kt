package com.robertomiranda.data.api

import com.robertomiranda.data.api.models.PostApi
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentsApi {

    @GET("comments")
    fun getAllComments(): Maybe<List<PostApi>>

    @GET("comments/{id}")
    fun getCommentsById(@Path("id") id: String): Maybe<PostApi>
}