package com.capstone.duitdojo_financeappmanagement.data.retrofit

import com.capstone.duitdojo_financeappmanagement.data.model.NewsResponse
import com.capstone.duitdojo_financeappmanagement.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/top-headlines")
    suspend fun getHeadline(
        @Query("country")
        countryCode: String = "id",
        @Query("category")
        category: String = "business",
        @Query("page")
        pageNumber: Int = 1,
        @Query("size")
        size: Int = 20,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

}