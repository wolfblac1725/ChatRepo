package com.erik.canseco.chatexamen.presentation.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeDataState,
    onClick: (String) -> Unit,
) {
    Box( modifier = Modifier.fillMaxSize()) {
        if(state.chats.isEmpty()){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(text = stringResource(R.string.empty_chat))
            }
        }else {
            Scaffold (
                topBar =
                {
                    TopAppBar(
                        title = {
                            Text(stringResource(R.string.app_name))
                        },
                    )
                },
                content = { paddingValues ->
                    LazyColumn (modifier = Modifier.padding(paddingValues).fillMaxSize().padding(16.dp)){
                        items(state.chats.size,
                        ) { chat ->
                            Column(
                                modifier = Modifier.fillMaxWidth()
                                    .height(80.dp)
                                    .padding(16.dp)
                                    .clickable {
                                        onClick(state.chats[chat].id)
                                    }
                            ){
                                Row (
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        state.chats[chat].name,
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier.weight(1f)
                                    )

                                    Icon(
                                        imageVector = Icons.Filled.ArrowForwardIos,
                                        contentDescription = null
                                    )

                                }
                            }
                        }
                    }

                }
            )

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