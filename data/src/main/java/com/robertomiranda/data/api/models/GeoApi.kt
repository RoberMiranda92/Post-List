package com.robertomiranda.data.api.models

import com.google.gson.annotations.SerializedName

data class GeoApi(

    @field:SerializedName("lng")
    val lng: String? = null,

    @field:SerializedName("lat")
    val lat: String? = null
)

