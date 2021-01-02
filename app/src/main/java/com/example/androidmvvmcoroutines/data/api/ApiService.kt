package com.example.androidmvvmcoroutines.data.api

import com.example.androidmvvmcoroutines.data.response.NewsResponse
import com.example.androidmvvmcoroutines.util.Constant
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    suspend fun getNews(
        @Query("q") q: String = "bitcoin",
        @Query("from") from: String = "2020-12-02",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = Constant.API_KEY): NewsResponse

}