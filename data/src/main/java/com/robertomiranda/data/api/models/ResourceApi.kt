package com.robertomiranda.data.api.models

import com.google.gson.annotations.SerializedName

data class ResourceApi(
    @field:SerializedName("key")
    val key: String,

    @field:SerializedName("value")
    val value: String
)