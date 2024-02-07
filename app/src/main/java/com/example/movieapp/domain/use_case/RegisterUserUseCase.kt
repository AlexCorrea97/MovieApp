package com.example.movieapp.domain.use_case

import com.example.movieapp.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth

class RegisterUserUseCase(
    private val repository: LoginRepository,
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        firebaseAuth: FirebaseAuth,
        onComplete: (Boolean) -> Unit,
    ) = repository.registerUser(email, password, firebaseAuth, onComplete)
}