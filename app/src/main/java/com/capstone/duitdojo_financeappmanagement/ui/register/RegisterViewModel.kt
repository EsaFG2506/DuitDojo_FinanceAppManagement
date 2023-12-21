package com.capstone.duitdojo_financeappmanagement.ui.register

import androidx.lifecycle.ViewModel
import com.capstone.duitdojo_financeappmanagement.repository.UserRepository

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun register(name: String, email: String, password: String, reconfirmPassword: String) = userRepository.register(name, email, password,reconfirmPassword)
}