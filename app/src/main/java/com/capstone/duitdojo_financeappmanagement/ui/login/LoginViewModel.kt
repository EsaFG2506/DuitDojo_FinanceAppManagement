package com.capstone.duitdojo_financeappmanagement.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capstone.duitdojo_financeappmanagement.data.model.UserModel
import com.capstone.duitdojo_financeappmanagement.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            userRepository.saveSession(user)
            Log.d(TAG, "Token saved: ${user.token}")
        }
    }

    fun login(email: String, password: String) = userRepository.login(email, password)

    companion object {
        private const val TAG = "LoginViewModel"
    }
}