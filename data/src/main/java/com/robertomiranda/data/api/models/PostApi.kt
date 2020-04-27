package com.robertomiranda.data.api.models

import com.google.gson.annotations.SerializedName

data class PostApi(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
