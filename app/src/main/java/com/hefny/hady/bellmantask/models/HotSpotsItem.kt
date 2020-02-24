package com.hefny.hady.bellmantask.models

import com.google.gson.annotations.SerializedName

data class HotSpotsItem(

	@field:SerializedName("short_description")
	val shortDescription: String? = null,

	@field:SerializedName("external_rating")
	val externalRating: List<Any?>? = null,

	@field:SerializedName("city")
	val city: Any? = null,

	@field:SerializedName("cuisineTypes")
	val cuisineTypes: List<Any?>? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("instagram")
	val instagram: Any? = null,

	@field:SerializedName("video")
	val video: Any? = null,

	@field:SerializedName("facebook_image")
	val facebookImage: Any? = null,

	@field:SerializedName("photos")
	val photos: List<String?>? = null,

	@field:SerializedName("features")
	val features: List<Any?>? = null,

	@field:SerializedName("twitter")
	val twitter: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("categories")
	val categories: List<CategoriesItem?>? = null,

	@field:SerializedName("menus")
	val menus: List<Any?>? = null,

	@field:SerializedName("arabic_description")
	val arabicDescription: Any? = null,

	@field:SerializedName("lat")
	val lat: String? = null,

	@field:SerializedName("views")
	val views: Int? = null,

	@field:SerializedName("offers")
	val offers: List<Any?>? = null,

	@field:SerializedName("arabic_short_description")
	val arabicShortDescription: Any? = null,

	@field:SerializedName("website")
	val website: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("lng")
	val lng: String? = null,

	@field:SerializedName("profile_photo")
	val profilePhoto: String? = null,

	@field:SerializedName("facebook")
	val facebook: Any? = null,

	@field:SerializedName("facebook_id")
	val facebookId: Any? = null,

	@field:SerializedName("phone")
	val phone: Any? = null,

	@field:SerializedName("arabic_name")
	val arabicName: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("price_of_two")
	val priceOfTwo: String? = null,

	@field:SerializedName("neighborhood")
	val neighborhood: Any? = null,

	@field:SerializedName("city_id")
	val cityId: Any? = null
)