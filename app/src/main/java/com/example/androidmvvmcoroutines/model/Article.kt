package com.example.androidmvvmcoroutines.model

data class Article(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: Any,
    val urlToImage:String? = null,
    val publishedAt:String,
    val content:String,
)
