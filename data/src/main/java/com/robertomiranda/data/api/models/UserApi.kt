package com.robertomiranda.data.api.models

import com.google.gson.annotations.SerializedName

data class UserApi(

    @field:SerializedName("website")
    val website: String,

    @field:SerializedName("address")
    val address: AddressApi,

    @field:SerializedName("phone")
    val phone: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("company")
    val company: CompanyApi,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("username")
    val username: String
)