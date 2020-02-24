package com.hefny.hady.bellmantask.models

import com.google.gson.annotations.SerializedName

data class HomeResponse(

    @field:SerializedName("status_code")
    val statusCode: Int? = null,

    @field:SerializedName("data")
    val data: Data? = null,

    @field:SerializedName("message")
    val message: String? = null
)