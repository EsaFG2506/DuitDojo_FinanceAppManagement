package com.capstone.duitdojo_financeappmanagement.data.retrofit

import com.capstone.duitdojo_financeappmanagement.data.model.LoginResponse
import com.capstone.duitdojo_financeappmanagement.data.model.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("api/users/")
    suspend fun register(
        @Field("username") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("reconfirmPassword") reconfirmPassword: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("api/users/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse
}