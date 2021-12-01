package com.buildappswithalejing.condorlabs_skill_test.network

import com.squareup.moshi.Json

data class MoviesData (
        val id: String,
        @Json(name = "img_src") val imgSrcUrl: String
        )
