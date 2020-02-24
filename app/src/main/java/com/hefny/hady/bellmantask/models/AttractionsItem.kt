package com.hefny.hady.bellmantask.models

import com.google.gson.annotations.SerializedName

data class AttractionsItem(

	@field:SerializedName("offers")
	val offers: List<Any?>? = null,

	@field:SerializedName("is_active")
	val isActive: Int? = null,

	@field:SerializedName("lng")
	val lng: String? = null,

	@field:SerializedName("markup")
	val markup: Any? = null,

	@field:SerializedName("city")
	val city: City? = null,

	@field:SerializedName("facebook")
	val facebook: Any? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("instagram")
	val instagram: Any? = null,

	@field:SerializedName("photos")
	val photos: List<String?>? = null,

	@field:SerializedName("duration_unit")
	val durationUnit: String? = null,

	@field:SerializedName("duration")
	val duration: String? = null,

	@field:SerializedName("features")
	val features: List<Any?>? = null,

	@field:SerializedName("twitter")
	val twitter: Any? = null,

	@field:SerializedName("tryps_id")
	val trypsId: String? = null,

	@field:SerializedName("terms")
	val terms: String? = null,

	@field:SerializedName("working_hours")
	val workingHours: List<WorkingHoursItem?>? = null,

	@field:SerializedName("arabic_name")
	val arabicName: String? = null,

	@field:SerializedName("general_terms")
	val generalTerms: String? = null,

	@field:SerializedName("exlcude")
	val exlcude: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("categories")
	val categories: List<CategoriesItem?>? = null,

	@field:SerializedName("arabic_description")
	val arabicDescription: String? = null,

	@field:SerializedName("lat")
	val lat: String? = null
)