package com.hefny.hady.bellmantask.models

import com.google.gson.annotations.SerializedName

data class Data(

	@field:SerializedName("hot_spots")
	val hotSpots: List<HotSpotsItem>? = null,

	@field:SerializedName("attractions")
	val attractions: List<AttractionsItem>? = null,

	@field:SerializedName("events")
	val events: List<Any?>? = null
)