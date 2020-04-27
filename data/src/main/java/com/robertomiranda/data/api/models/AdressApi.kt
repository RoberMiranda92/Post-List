package com.robertomiranda.data.api.models

import com.google.gson.annotations.SerializedName

data class AddressApi(

    @field:SerializedName("zipcode")
    val zipcode: String? = null,

    @field:SerializedName("geo")
    val geo: GeoApi? = null,

    @field:SerializedName("suite")
    val suite: String? = null,

    @field:SerializedName("city")
    val city: String? = null,

    @field:SerializedName("street")
    val street: String? = null
)