package com.erik.canseco.chatexamen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.erik.canseco.chatexamen.navigation.NavigationRoot
import com.erik.canseco.chatexamen.ui.theme.ChatExamenTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var  navController : NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            Surface (modifier = Modifier.fillMaxSize()) {
                ChatExamenTheme {
                    navController = rememberNavController()
                    NavigationRoot(navController)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }
}
