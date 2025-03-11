package com.erik.canseco.chatexamen.presentation.screen.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class DetailChatViewModel: ViewModel() {
    val state by mutableStateOf(DetailChatDataState())

}