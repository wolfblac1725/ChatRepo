package com.erik.canseco.chatexamen.presentation.screen.login


data class LoginDataState(
    var userName: String = "",
    var password: String = "",
    val isUserNameValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isErrorLogin: Boolean = false,
    val isLogging: Boolean = false
){
    fun isLoginButtonEnabled(): Boolean {
        return userName.isNotEmpty() && password.isNotEmpty()

    }

}