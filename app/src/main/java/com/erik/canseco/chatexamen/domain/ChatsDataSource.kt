package com.erik.canseco.chatexamen.domain

import com.erik.canseco.chatexamen.data.entity.Chat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

interface ChatsDataSource {
    suspend fun getChats(idUser: String, auth: FirebaseAuth, db : FirebaseFirestore): List<Chat>
}