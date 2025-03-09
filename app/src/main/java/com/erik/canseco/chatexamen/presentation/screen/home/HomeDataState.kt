package com.erik.canseco.chatexamen.presentation.screen.home

import com.erik.canseco.chatexamen.data.entity.Chat

data class HomeDataState(
    val chat: List<Chat> = emptyList(),
    val isError: Boolean = false,
)
