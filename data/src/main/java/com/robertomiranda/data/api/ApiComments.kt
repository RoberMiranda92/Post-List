package com.robertomiranda.data.api

import com.robertomiranda.data.api.models.CommentApi
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiComments {

    @GET("comments")
    fun getAllComments(): Maybe<List<CommentApi>>

    @GET("comments/{id}")
    fun getCommentsById(@Path("id") id: String): Maybe<CommentApi>
}