package com.erik.canseco.chatexamen.presentation.screen.detail

import com.erik.canseco.chatexamen.data.entity.Messages

data class DetailChatDataState(
    val messages:List<Messages> = emptyList(),
    val title: String = "",
)
