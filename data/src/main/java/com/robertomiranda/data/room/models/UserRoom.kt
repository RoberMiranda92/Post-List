package com.robertomiranda.data.room.models

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class UserRoom(

    @field:SerializedName("website")
    val website: String? = null,

    @field:SerializedName("address")
    val address: AddressRoom? = null,

    @field:SerializedName("phone")
    val phone: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("company")
    val company: CompanyRoom? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("username")
    val username: String? = null
)