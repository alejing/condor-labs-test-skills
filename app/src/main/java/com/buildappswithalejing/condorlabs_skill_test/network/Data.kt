package com.buildappswithalejing.condorlabs_skill_test.network

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Data(
        @SerializedName(value = "page") var page: Int,
        @SerializedName(value = "results") var results: List<Movie>,
        @SerializedName(value = "total_results") var totalResults: Int,
        @SerializedName(value = "total_pages") var totalPages: Int
)

data class Movie(
        @SerializedName(value = "poster_path") var posterPath: String,
        @SerializedName(value = "adult")  var adult: Boolean,
        @SerializedName(value = "overview")  var overview: String,
        @SerializedName(value = "release_date") var releaseDate: String,
        @SerializedName(value = "genre_ids") var genreIDs: List<Int>,
        @SerializedName(value = "id") var id: Int,
        @SerializedName(value = "original_title") var originalTitle: String,
        @SerializedName(value = "original_language") var originalLanguage: String,
        @SerializedName(value = "title") var title: String,
        @SerializedName(value = "backdrop_path") var backdropPath: String,
        @SerializedName(value = "popularity") var popularity: Number,
        @SerializedName(value = "vote_count") var voteCount: Int,
        @SerializedName(value = "video") var video: Boolean,
        @SerializedName(value = "vote_average") var voteAverage: Number
)