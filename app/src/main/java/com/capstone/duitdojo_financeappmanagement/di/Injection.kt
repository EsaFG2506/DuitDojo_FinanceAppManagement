package com.capstone.duitdojo_financeappmanagement.di

import android.content.Context
import com.capstone.duitdojo_financeappmanagement.data.pref.UserPreference
import com.capstone.duitdojo_financeappmanagement.data.pref.dataStore
import com.capstone.duitdojo_financeappmanagement.data.retrofit.ApiConfig
import com.capstone.duitdojo_financeappmanagement.data.retrofit.NotaConfig
import com.capstone.duitdojo_financeappmanagement.data.room.PhotoDB
import com.capstone.duitdojo_financeappmanagement.repository.PhotoRepository
import com.capstone.duitdojo_financeappmanagement.repository.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideUserRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService(user.token)
        return UserRepository.getInstance(pref, apiService)
    }

    fun providePhotoRepository(context: Context): PhotoRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val notaService = NotaConfig.getNotaService(user.token)
        val database = PhotoDB.getDatabase(context)
        val dao= database.photoDao()
        return PhotoRepository.getInstance(pref, notaService, dao)
    }
}