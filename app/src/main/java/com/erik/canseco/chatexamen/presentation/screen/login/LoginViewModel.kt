package com.erik.canseco.chatexamen.presentation.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth


class LoginViewModel : ViewModel() {

    var state by mutableStateOf(LoginDataState())
        private set

    fun onUserNameChanged(userName: String) {
        state = state.copy(userName = userName)
    }

    fun onPasswordChanged(password: String) {
        state = state.copy(password = password)
    }

    fun onErrorMessageSet(error: Boolean = false) {
        state = state.copy(
            isErrorLogin = error
        )
    }
    fun onLoginClicked(
        navigateToHome: () -> Unit,
        auth: FirebaseAuth
    ) {
        auth.signInWithEmailAndPassword(state.userName, state.password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                navigateToHome()
            } else {
               onErrorMessageSet(true)
            }
        }
    }
}
