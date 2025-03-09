package com.erik.canseco.chatexamen.data

import android.util.Log
import com.erik.canseco.chatexamen.data.entity.Chat
import com.erik.canseco.chatexamen.data.entity.DocumentTOChat
import com.erik.canseco.chatexamen.domain.ChatsDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore

object FirebaseChatsDataSource: ChatsDataSource {
    override suspend fun getChats(
        idUser: String,
        auth: FirebaseAuth,
        db: FirebaseFirestore
    ): List<Chat> {
        val listChat = mutableListOf<Chat>()
        try {
            db.collection("chats").where(Filter.or(
                Filter.equalTo("user1", idUser),
                Filter.equalTo("user2", idUser)
            ))
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        Log.d("FirebaseChatsDataSource", "${document.id} => ${document.data}")
                        listChat.add(DocumentTOChat(document))
                    }
                }.addOnFailureListener { exception ->
                    Log.w("FirebaseChatsDataSource", "Error getting documents.", exception)
                }
        }catch (e: Exception){
            Log.e("FirebaseChatsDataSource", e.message.toString())
        }
        return listChat
    }

}