package com.erik.canseco.chatexamen.presentation.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erik.canseco.chatexamen.domain.AuthDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
 private val auth : AuthDataSource
) : ViewModel() {

    var state by mutableStateOf(LoginDataState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(
                isLogging = auth.current != null
            )
        }
    }

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
    ) {
        viewModelScope.launch {
            auth.loginWithEmailAndPassword(state.userName.trim(), state.password.trim(), {
                isLogin ->
                if (isLogin){
                    navigateToHome()
                } else {
                    onErrorMessageSet(true)
                }
            })
        }
    }
}
