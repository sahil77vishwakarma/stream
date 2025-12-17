package com.example.stream.util

sealed class RegisterValidation() {
    object Success: RegisterValidation()
    data class Failed(val message: String, val check : Int = 0): RegisterValidation()
}

data class RegisterFieldState(
    val email: RegisterValidation,
    val password: RegisterValidation,
    val name : RegisterValidation
)