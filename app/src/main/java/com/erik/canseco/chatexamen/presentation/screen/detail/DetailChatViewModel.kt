package com.erik.canseco.chatexamen.presentation.screen.detail

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
class DetailChatViewModel @Inject constructor(
    private val chats: ChatsDataSource,
): ViewModel() {
    var state by mutableStateOf(DetailChatDataState())
        private set


    fun  getChat(id: String){
        viewModelScope.launch {
            chats.getChat(id){ chat ->
                state = state.copy(
                    title = chat.name,
                    messages = chat.messages
                )
            }
        }
    }

}