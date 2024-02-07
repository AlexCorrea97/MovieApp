package com.example.movieapp.domain.use_case

import com.example.movieapp.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth

class LoginUseCase(val repository: LoginRepository) {

    suspend operator fun invoke(
        email: String,
        password: String,
        auth: FirebaseAuth,
        onComplete: (Boolean) -> Unit,
    ) = repository.onLogin(email, password, auth, onComplete)
}