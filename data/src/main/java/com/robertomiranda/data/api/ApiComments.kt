package com.robertomiranda.data.api

import com.robertomiranda.data.api.models.CommentApi
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiComments {

    @GET("comments")
    fun getAllComments(): Flowable<List<CommentApi>>

    @GET("comments/{id}")
    fun getCommentsById(@Path("id") id: String): Flowable<CommentApi>
}