package com.robertomiranda.data.models

import com.robertomiranda.data.api.models.ResourceApi
import io.reactivex.Flowable
import retrofit2.http.GET

data class Resource(val key: String, val value: String)