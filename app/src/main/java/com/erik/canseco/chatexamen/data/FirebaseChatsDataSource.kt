package com.erik.canseco.chatexamen.data


import com.erik.canseco.chatexamen.data.entity.Chat
import com.erik.canseco.chatexamen.domain.ChatsDataSource
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.getValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class FirebaseChatsDataSource @Inject constructor(
    private val database: DatabaseReference,
) : ChatsDataSource {
    val chatList = MutableStateFlow(emptyList<Chat>())
    override val listChats: Flow<List<Chat>>
        get() = chatList


    override suspend fun getChatsUser() {
        database.get().addOnCompleteListener {
            if (it.isSuccessful) {
                it.result.getValue<List<Chat>>()?.let { listChat ->
                    chatList.value = listChat
                }
            }
        }
    }

override suspend fun getChat(id: String, onChat: (Chat) -> Unit) {
    database.child((id.toInt()-1).toString())
        .get().addOnCompleteListener {
            if (it.isSuccessful) {
                it.result.getValue<Chat>()?.let { listChat ->
                    onChat(listChat)
                }
            }
        }
    }

}