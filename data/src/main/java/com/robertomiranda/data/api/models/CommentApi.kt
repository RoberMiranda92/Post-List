package com.robertomiranda.data.api.models

import com.google.gson.annotations.SerializedName

data class CommentApi(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("postId")
    val postId: Int,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("body")
    val body: String,

    @field:SerializedName("email")
    val email: String
)
