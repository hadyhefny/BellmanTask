package com.hefny.hady.bellmantask.models

import com.google.gson.annotations.SerializedName

data class City(

	@field:SerializedName("lng")
	val lng: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("zoom")
	val zoom: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("lat")
	val lat: Any? = null
)