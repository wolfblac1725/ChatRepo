package com.erik.canseco.chatexamen.presentation.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erik.canseco.chatexamen.domain.ChatsDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val chats: ChatsDataSource
) : ViewModel(){
    var state by mutableStateOf(HomeDataState())
        private set
    init {
        viewModelScope.launch {
            chats.getChatsUser()
        }
        viewModelScope.launch {
            chats.listChats.collect{
                state = state.copy(
                    chats = it.toMutableList()
                )
            }

        }
    }

}