package com.erik.canseco.chatexamen.domain

import com.google.firebase.auth.FirebaseUser

interface AuthDataSource {
    val current:FirebaseUser?
    suspend fun loginWithEmailAndPassword(email: String, password: String,onResult: (Boolean) -> Unit)
}