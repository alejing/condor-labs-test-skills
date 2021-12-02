package com.buildappswithalejing.condorlabs_skill_test.network

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

private const val BASE_URL_API =
    "https://api.themoviedb.org"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
/**
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
*/
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL_API)
    .build()

interface TMDBService{
    @GET("/3/movie/popular?api_key=7ea095ca8a19451e8674dd26580bd42c")
    suspend fun getAllMovies(): Data

    @GET("/3/movie/{idMovie}?api_key=7ea095ca8a19451e8674dd26580bd42c&append_to_response=videos")
    suspend fun getMovie(
        @Path(value = "idMovie") idNumber: String
    ): DataOneMovie
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object MoviesApi {
    val retrofitService: TMDBService by lazy {
        retrofit.create(TMDBService::class.java)
    }
}