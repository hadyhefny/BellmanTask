package com.hefny.hady.bellmantask.models

import com.google.gson.annotations.SerializedName

data class WorkingHoursItem(

	@field:SerializedName("date")
	val date: Any? = null,

	@field:SerializedName("from")
	val from: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("to")
	val to: String? = null
)