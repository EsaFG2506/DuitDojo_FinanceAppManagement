package com.capstone.duitdojo_financeappmanagement.repository

import com.capstone.duitdojo_financeappmanagement.data.retrofit.NewsConfig
import com.capstone.duitdojo_financeappmanagement.data.room.ArticleDatabase

class NewsRepository(val db: ArticleDatabase) {
    suspend fun geHeadlines(countryCode: String, category: String, pageNumber: Int) =
        NewsConfig.newsApi.getHeadline(countryCode, category,pageNumber)
}