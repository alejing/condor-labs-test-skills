package com.buildappswithalejing.condorlabs_skill_test.network

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface TMDBService{
    @GET("photos")
    suspend fun getMovies(): List<MoviesData>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object MoviesApi {
    val retrofitService: TMDBService by lazy {
        retrofit.create(TMDBService::class.java)
    }
}