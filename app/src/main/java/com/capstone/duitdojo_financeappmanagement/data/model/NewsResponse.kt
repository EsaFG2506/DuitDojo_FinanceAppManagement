package com.capstone.duitdojo_financeappmanagement.data.model

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)