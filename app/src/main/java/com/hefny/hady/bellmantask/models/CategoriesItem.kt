package com.hefny.hady.bellmantask.models

import com.google.gson.annotations.SerializedName

data class CategoriesItem(

	@field:SerializedName("cover_image_url")
	val coverImageUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("icon_image_url")
	val iconImageUrl: String? = null,

	@field:SerializedName("ar_name")
	val arName: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null
)