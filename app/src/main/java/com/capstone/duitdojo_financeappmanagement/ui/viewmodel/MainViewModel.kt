package com.capstone.duitdojo_financeappmanagement.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.duitdojo_financeappmanagement.data.model.UserModel
import com.capstone.duitdojo_financeappmanagement.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return userRepository.getSession().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            userRepository.logout()
            Log.d(TAG, "Token removed")
        }
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}