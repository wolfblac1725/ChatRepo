package com.erik.canseco.chatexamen.data.entity

import com.google.firebase.firestore.DocumentSnapshot
import org.w3c.dom.DocumentType

data class Chat(
    val id : String,
    val name: String,
    val user1: String,
    val user2: String,
    val messages: List<Messages>
)

fun DocumentTOChat(document: DocumentSnapshot): Chat {
    return Chat(
        id = document.id,
        name = document.getString("name") ?: "",
        user1 = document.getString("user1") ?: "",
        user2 = document.getString("user2") ?: "",
        messages = document.get("messages") as List<Messages>
    )
}
