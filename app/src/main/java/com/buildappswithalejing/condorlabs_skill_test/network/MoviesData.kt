package com.buildappswithalejing.condorlabs_skill_test.network

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class MoviesData (
        val id: String,
        @SerializedName(value = "img_src") val imgSrcUrl: String
        )
/**
data class MoviesData (
        val id: String,
        @Json(name = "img_src") val imgSrcUrl: String
)
*/