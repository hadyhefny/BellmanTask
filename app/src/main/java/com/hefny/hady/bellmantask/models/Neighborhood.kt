package com.hefny.hady.bellmantask.models

import com.google.gson.annotations.SerializedName

data class Neighborhood(

	@field:SerializedName("lng")
	val lng: Any? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("arabic_name")
	val arabicName: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("zoom")
	val zoom: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("lat")
	val lat: Any? = null,

	@field:SerializedName("city_id")
	val cityId: Any? = null
)