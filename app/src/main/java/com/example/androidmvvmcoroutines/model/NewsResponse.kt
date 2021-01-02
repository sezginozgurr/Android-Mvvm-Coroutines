package com.example.androidmvvmcoroutines.model

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: ArrayList<Article>
)
