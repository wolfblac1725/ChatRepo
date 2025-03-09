package com.erik.canseco.chatexamen.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.erik.canseco.chatexamen.presentation.screen.detail.DetailChatScreen
import com.erik.canseco.chatexamen.presentation.screen.home.HomeScreenRoot
import com.erik.canseco.chatexamen.presentation.screen.home.HomeViewModel
import com.erik.canseco.chatexamen.presentation.screen.login.LoginScreenRoot
import com.erik.canseco.chatexamen.presentation.screen.login.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun NavigationRoot(
    navController: NavHostController,
    auth: FirebaseAuth,
    db: FirebaseFirestore
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        NavHost(
            navController = navController,
            startDestination = Login
        ){
            composable<Login> {
                val viewModel = LoginViewModel()
                LoginScreenRoot(
                    viewModel = viewModel,
                    navigateToHome = {
                        navController.navigate(Home)
                    },
                    auth = auth,
                )
            }
            composable<Home> {
                val viewModel = HomeViewModel(auth = auth,
                    db = db)
                HomeScreenRoot(
                    viewModel = viewModel,
                    detailChat = { id ->
                        navController.navigate(DetailChat(id))
                    }
                )
            }
            composable<DetailChat> { backStackEntry ->
                val detailId: DetailChat = backStackEntry.toRoute()
                DetailChatScreen(
                    indexChat= detailId.id,
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }

        }
    }
}
