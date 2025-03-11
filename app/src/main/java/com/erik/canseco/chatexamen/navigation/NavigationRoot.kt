package com.erik.canseco.chatexamen.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.erik.canseco.chatexamen.presentation.screen.detail.DetailChatScreenRoot
import com.erik.canseco.chatexamen.presentation.screen.detail.DetailChatViewModel
import com.erik.canseco.chatexamen.presentation.screen.home.HomeScreenRoot
import com.erik.canseco.chatexamen.presentation.screen.home.HomeViewModel
import com.erik.canseco.chatexamen.presentation.screen.login.LoginScreenRoot
import com.erik.canseco.chatexamen.presentation.screen.login.LoginViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        val start = if(Firebase.auth.currentUser?.uid == null) Login else Home
        NavHost(
            navController = navController,
            startDestination = start
        ){
            composable<Login> {
                val viewModel: LoginViewModel = hiltViewModel()
                LoginScreenRoot(
                    viewModel = viewModel,
                    navigateToHome = {
                        navController.navigate(Home)
                    }
                )
            }
            composable<Home> {
                val viewModel: HomeViewModel = hiltViewModel()
                HomeScreenRoot(
                    viewModel = viewModel,
                    detailChat = { id ->
                        navController.navigate(DetailChat(id))
                    }
                )
            }
            composable<DetailChat> { backStackEntry ->
                val detailId: DetailChat = backStackEntry.toRoute()
                val viewModel: DetailChatViewModel = hiltViewModel()
                DetailChatScreenRoot(
                    indexChat= detailId.id,
                    onBack = {
                        navController.popBackStack()
                    },
                    viewModel = viewModel
                )
            }

        }
    }
}
