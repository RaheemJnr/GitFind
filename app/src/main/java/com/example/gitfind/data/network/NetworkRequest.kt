package com.example.gitfind.data.network


import com.example.gitfind.data.network.model.GithubResponses
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.github.com/search/"


const val API_KEY = "034da672af3e87a27b2bfb04a03baaa1"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Main entry point for network access.
 */
object GitFindApiCall {
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    //
    val GIT_FIND_SERVICE: GitFindService by lazy {
        retrofit.create(GitFindService::class.java)
    }

//    //
//    val WEATHER_DETAIL_SERVICE: GetWeatherDetailsService by lazy {
//        retrofit.create(GetWeatherDetailsService::class.java)
//    }
}

//repositories?q=stars:%3E=10000+language:android&sort=stars&order=desc

/**
 * A retrofit service to fetch list of github repo data
 * for listScreen and a single weather data for details screen
 */
interface GitFindService {
    @GET("repositories")
    suspend fun getGitRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String = "stars"
    ): GithubResponses
}

////
//interface GetWeatherDetailsService {
//    @GET("forecast?q=harare&units=metric&cnt=1&appid=$API_KEY")
//    suspend fun getWeatherDataDetails(): WeatherListResponse
//}