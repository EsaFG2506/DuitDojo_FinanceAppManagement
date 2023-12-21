package com.capstone.duitdojo_financeappmanagement.data.model

data class UserModel (
    val email: String,
    val username: String,
    val token: String,
    val isLogin: Boolean = false
)