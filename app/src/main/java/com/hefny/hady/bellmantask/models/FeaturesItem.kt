package com.hefny.hady.bellmantask.models

import com.google.gson.annotations.SerializedName

data class FeaturesItem(

	@field:SerializedName("arabic_name")
	val arabicName: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)