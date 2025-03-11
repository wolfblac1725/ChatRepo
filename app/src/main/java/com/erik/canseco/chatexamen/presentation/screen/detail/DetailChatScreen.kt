package com.erik.canseco.chatexamen.presentation.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DetailChatScreenRoot(
    viewModel: DetailChatViewModel,
    indexChat: String,
    onBack: () -> Unit,
) {
    val state = viewModel.state

    DetailChatScreen(
        state = state,
        indexChat = indexChat,
        onBack = onBack
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailChatScreen(
    state: DetailChatDataState,
    indexChat: String,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = indexChat)
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            onBack()
                        }
                    )
                }
            )
        },
        content = { paddingValues ->
            Text(text = "Contenido del chat $indexChat", modifier = Modifier.padding(paddingValues))

        },
    )

}