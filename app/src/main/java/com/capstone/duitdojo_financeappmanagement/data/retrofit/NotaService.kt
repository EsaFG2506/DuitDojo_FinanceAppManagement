package com.capstone.duitdojo_financeappmanagement.data.retrofit

import com.capstone.duitdojo_financeappmanagement.data.model.PhotoResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface NotaService {

    @GET("donut/")
    suspend fun getNota(): PhotoResponse

    @Multipart
    @POST("donut/")
    fun uploadImage(
        @Part image: MultipartBody.Part
    ): Call<PhotoResponse>
}