package com.robertomiranda.data.api

import com.robertomiranda.data.api.models.ResourceApi
import io.reactivex.Flowable
import retrofit2.http.GET

interface ApiResources {

    @GET("ResourceApi")
    fun getResources(): Flowable<List<ResourceApi>>
}