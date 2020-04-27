package com.robertomiranda.data.api.models

import com.google.gson.annotations.SerializedName

data class UserApi(

    @field:SerializedName("website")
    val website: String? = null,

    @field:SerializedName("address")
    val address: AddressApi? = null,

    @field:SerializedName("phone")
    val phone: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("company")
    val company: CompanyApi? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("username")
    val username: String? = null
)