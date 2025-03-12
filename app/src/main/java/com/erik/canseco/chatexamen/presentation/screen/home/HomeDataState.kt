package com.erik.canseco.chatexamen.presentation.screen.home

import com.erik.canseco.chatexamen.data.entity.Chat

data class HomeDataState(
    val chats: MutableList<Chat> = mutableListOf(),
    val isError: Boolean = false,
)
