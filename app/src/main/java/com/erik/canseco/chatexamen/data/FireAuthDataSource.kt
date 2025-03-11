package com.erik.canseco.chatexamen.data

import com.erik.canseco.chatexamen.domain.AuthDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class FireAuthDataSource @Inject constructor(
    private val fireAuth: FirebaseAuth
): AuthDataSource {

    override val current: FirebaseUser?
        get() = fireAuth.currentUser


    override suspend fun loginWithEmailAndPassword(email: String, password: String,onResult: (Boolean) -> Unit) {
       fireAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
           if(task.isSuccessful){
               onResult(true)
           }else{
               onResult(false)
           }
       }
    }

}