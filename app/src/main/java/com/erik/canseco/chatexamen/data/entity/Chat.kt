package com.erik.canseco.chatexamen.data.entity

data class Chat(
    val id : String = "",
    val name: String = "",
    val user1: String= "",
    val user2: String = "",
    val messages: List<Messages> = emptyList(),
)