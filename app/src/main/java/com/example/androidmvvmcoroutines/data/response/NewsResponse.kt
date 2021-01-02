package com.example.androidmvvmcoroutines.data.response

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: ArrayList<Article>
)
