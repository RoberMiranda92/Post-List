package com.robertomiranda.data.api.models

import com.google.gson.annotations.SerializedName

data class CompanyApi(

    @field:SerializedName("bs")
    val bs: String? = null,

    @field:SerializedName("catchPhrase")
    val catchPhrase: String? = null,

    @field:SerializedName("name")
    val name: String? = null
)