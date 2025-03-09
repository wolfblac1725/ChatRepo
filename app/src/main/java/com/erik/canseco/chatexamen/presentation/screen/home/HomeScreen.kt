package com.erik.canseco.chatexamen.presentation.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erik.canseco.chatexamen.R
import com.erik.canseco.chatexamen.ui.theme.ChatExamenTheme

@Composable
fun HomeScreenRoot(
    viewModel: HomeViewModel,
    detailChat: (id: String) -> Unit,
   ){

    val state = viewModel.state
    HomeScreen(
        state,
        onClick = detailChat
    )

}
@Composable
fun HomeScreen(
    state: HomeDataState,
    onClick: (String) -> Unit,
) {
    Box( modifier = Modifier.fillMaxSize()) {
        if(state.chat.isEmpty()){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(text = stringResource(R.string.empty_chat))
            }
        }else {
            LazyColumn {
                items(
                    state.chat.size,
                ) { chat ->
                    Column (
                        modifier = Modifier.fillMaxWidth().padding(16.dp).clickable {
                            onClick( state.chat[chat].id)
                        }
                    ){
                        Text(state.chat[chat].name)
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreviewLight() {
    ChatExamenTheme {
        HomeScreen(
            HomeDataState(),
            {}
        )
    }
}