package com.example.movieapp.data.repository

import com.example.movieapp.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth

class LoginRepositoryImp : LoginRepository{
    override suspend fun registerUser(
        email: String,
        password: String,
        auth: FirebaseAuth,
        onComplete : (Boolean)->Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            onComplete(it.isSuccessful)
        }
    }

    override suspend fun onLogin(
        email: String,
        password: String,
        auth: FirebaseAuth,
        onComplete: (Boolean) -> Unit,
    ) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            onComplete(it.isSuccessful)
        }
    }


}