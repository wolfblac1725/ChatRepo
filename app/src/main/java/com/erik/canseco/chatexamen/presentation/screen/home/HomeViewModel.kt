package com.erik.canseco.chatexamen.presentation.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erik.canseco.chatexamen.data.FirebaseChatsDataSource
import com.erik.canseco.chatexamen.data.entity.Chat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
): ViewModel(){
    var state by mutableStateOf(HomeDataState())
        private set

    init {
        state = state.copy(
            chat = getChats()

        )
    }

    fun getChats(
    ): List<Chat> {
        val listChat = mutableListOf<Chat>()
        viewModelScope.launch {
            val chats = withContext(Dispatchers.IO) {
                auth.currentUser?.let { FirebaseChatsDataSource.getChats(it.uid, auth, db) }
            }
        }
        return listChat
    }
}