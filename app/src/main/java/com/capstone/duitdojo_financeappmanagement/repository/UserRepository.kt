package com.capstone.duitdojo_financeappmanagement.repository

import androidx.lifecycle.liveData
import com.capstone.duitdojo_financeappmanagement.data.model.ErrorResponse
import com.capstone.duitdojo_financeappmanagement.data.model.UserModel
import com.capstone.duitdojo_financeappmanagement.data.pref.UserPreference
import com.capstone.duitdojo_financeappmanagement.data.retrofit.ApiService
import com.capstone.duitdojo_financeappmanagement.utils.Result
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class UserRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService
) {
    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

    fun register(username: String, email: String, password: String, reconfirmPassword: String) = liveData {
        emit(Result.Loading)
        try {
            val message = apiService.register(username, email, password, reconfirmPassword).message
            emit(Result.Success(message))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(errorMessage?.let { Result.Error(it) })
        }
    }

    fun login(email: String, password: String) = liveData {
        emit(Result.Loading)
        try {
            val successResponse = apiService.login(email, password)
            emit(Result.Success(successResponse))
            val username = successResponse.username ?: ""
            val token = successResponse.token ?: ""
            val userModel = UserModel(email, username, token, true)
            userPreference.saveSession(userModel)
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(errorMessage?.let { Result.Error(it) })
        }
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference, apiService)
            }.also { instance = it }
    }
}