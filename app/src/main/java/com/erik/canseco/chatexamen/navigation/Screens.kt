package com.erik.canseco.chatexamen.navigation

import kotlinx.serialization.Serializable

@Serializable
object Login
@Serializable
object Home
@Serializable
data class DetailChat(
    val id: String,
)