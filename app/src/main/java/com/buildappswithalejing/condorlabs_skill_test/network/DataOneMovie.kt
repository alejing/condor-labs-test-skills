package com.buildappswithalejing.condorlabs_skill_test.network

import com.google.gson.annotations.SerializedName

data class DataOneMovie (
    @SerializedName(value = "adult") var adult: Boolean,
    @SerializedName(value = "backdrop_path") var backdropPath: String,
    @SerializedName(value = "belongs_to_collection") var belongs2collection: BCollection,
    @SerializedName(value = "budget") var budget: Int,
    @SerializedName(value = "genres") var genres: List<Genre>,
    @SerializedName(value = "homepage") var homepage: String,
    @SerializedName(value = "id") var id: Int,
    @SerializedName(value = "imdb_id") var imdbId: String,
    @SerializedName(value = "original_language") var originaLanguage: String,
    @SerializedName(value = "original_title") var originalTitle: String,
    @SerializedName(value = "overview") var overview: String,
    @SerializedName(value = "popularity") var popularity: Number,
    @SerializedName(value = "poster_path") var posterPath: String,
    @SerializedName(value = "production_companies") var productionCompanies: List<Company>,
    @SerializedName(value = "production_countries") var productionCountries: List<Country>,
    @SerializedName(value = "release_date") var releaseDate: String,
    @SerializedName(value = "revenue") var revenue: Int,
    @SerializedName(value = "runtime") var runtime: Int,
    @SerializedName(value = "spoken_languages") var spokenLanguages: List<Languages>,
    @SerializedName(value = "status") var status: String,
    @SerializedName(value = "tagline") var tagline: String,
    @SerializedName(value = "title") var title: String,
    @SerializedName(value = "video") var video: Boolean,
    @SerializedName(value = "vote_average") var voteAverage: Number,
    @SerializedName(value = "vote_count") var voteCount: Int
)

data class BCollection(
    @SerializedName(value = "id") var id: Int,
    @SerializedName(value = "name") var name: String,
    @SerializedName(value = "poster_path") var posterPath: String,
    @SerializedName(value = "backdrop_path") var backdropPath: String
        )

data class Genre(
    @SerializedName(value = "id") var id: Int,
    @SerializedName(value = "name") var name: String
)

data class Company(
    @SerializedName(value = "id") var id: Int,
    @SerializedName(value = "logo_path") var logoPath: String,
    @SerializedName(value = "name") var name: String,
    @SerializedName(value = "origin_country") var originCountry: String
)

data class Country(
    @SerializedName(value = "iso_3166_1") var iso3166_1: String,
    @SerializedName(value = "name") var name: String
)

data class Languages(
    @SerializedName(value = "english_name") var englishName: String,
    @SerializedName(value = "iso_639_1") var iso639_1: String,
    @SerializedName(value = "name") var name: String
)