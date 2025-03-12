package com.erik.canseco.chatexamen.presentation.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.erik.canseco.chatexamen.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun DetailChatScreenRoot(
    viewModel: DetailChatViewModel,
    indexChat: String,
    onBack: () -> Unit,
) {
    val state = viewModel.state
    viewModel.getChat(indexChat)
    DetailChatScreen(
        state = state,
        onBack = onBack
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailChatScreen(
    state: DetailChatDataState,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = state.title,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                },
                navigationIcon = {
                    Image(
                        painter = painterResource(R.drawable.ic_arrow_back),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            onBack()
                        }
                    )
                }
            )
        },
        content = { paddingValues ->
            LazyColumn(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
                items(state.messages.size) { index ->
                    if(state.messages[index].userId == Firebase.auth.currentUser?.uid){
                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd
                        ){
                            Text(
                                modifier = Modifier
                                    .padding(16.dp),
                                text = "Yo: ${state.messages[index].messages}",
                                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                            )
                        }

                    }else{
                        Box(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.CenterStart
                        ){
                            Text(
                                modifier = Modifier
                                    .padding(16.dp),
                                text = "El: ${state.messages[index].messages}",
                                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }

            }
        }
    )
}