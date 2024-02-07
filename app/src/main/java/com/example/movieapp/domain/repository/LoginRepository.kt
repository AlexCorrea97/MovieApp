package com.example.movieapp.domain.repository

import com.google.firebase.auth.FirebaseAuth

interface LoginRepository {
    suspend fun registerUser(email: String, password:String, auth: FirebaseAuth, onComplete:(Boolean) ->Unit)

    suspend fun onLogin(email: String, password:String, auth: FirebaseAuth, onComplete:(Boolean) ->Unit)
}