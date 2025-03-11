package com.erik.canseco.chatexamen.domain

import com.erik.canseco.chatexamen.data.entity.Chat
import kotlinx.coroutines.flow.Flow

interface ChatsDataSource {
    val listChats: Flow<List<Chat>>
    suspend fun getChatsUser()
}