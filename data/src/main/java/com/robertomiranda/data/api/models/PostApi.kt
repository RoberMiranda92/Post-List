package com.robertomiranda.data.api.models

import com.google.gson.annotations.SerializedName

data class PostApi(

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("userId")
    val userId: Int,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("body")
    val body: String
)
