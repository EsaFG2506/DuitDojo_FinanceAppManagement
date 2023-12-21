package com.capstone.duitdojo_financeappmanagement.repository

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.liveData
import com.capstone.duitdojo_financeappmanagement.data.model.ErrorResponse
import com.capstone.duitdojo_financeappmanagement.data.model.Status
import com.capstone.duitdojo_financeappmanagement.data.model.UserModel
import com.capstone.duitdojo_financeappmanagement.data.pref.UserPreference
import com.capstone.duitdojo_financeappmanagement.data.retrofit.NotaService
import com.capstone.duitdojo_financeappmanagement.data.room.PhotoDao
import com.capstone.duitdojo_financeappmanagement.utils.Result
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File

class PhotoRepository private constructor(
    private val userPreference: UserPreference,
    private val notaService: NotaService,
    private val photoDao: PhotoDao
)  {

    fun uploadPhoto(imageFile: File) = liveData {
        try {
            emit(Result.Loading)
            val result = withContext(Dispatchers.IO) {
                val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
                val multipartBody = MultipartBody.Part.createFormData("photo", imageFile.name, requestImageFile)
                notaService.uploadImage(multipartBody).execute()
            }

            emit(Result.Success(result))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, Status::class.java)
            val errorMessage = errorBody.message
            emit(errorMessage.let { Result.Error(it) })
        }
    }

    companion object {
        @Volatile
        private var instance: PhotoRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            notaService: NotaService,
            photoDao: PhotoDao
        ): PhotoRepository =
            instance ?: synchronized(this) {
                instance ?: PhotoRepository(userPreference, notaService, photoDao)
            }.also { instance = it }
    }
}