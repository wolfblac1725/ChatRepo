package com.erik.canseco.chatexamen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.erik.canseco.chatexamen.navigation.Home
import com.erik.canseco.chatexamen.navigation.NavigationRoot
import com.erik.canseco.chatexamen.ui.theme.ChatExamenTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    private lateinit var  navController : NavHostController
    private lateinit var  auth : FirebaseAuth
    private lateinit var  db : FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        db = Firebase.firestore
        enableEdgeToEdge()
        setContent {
            Surface (modifier = Modifier.fillMaxSize()) {
                ChatExamenTheme {
                    navController = rememberNavController()
                    val currentUser = auth.currentUser
                    if(currentUser != null){
                        Log.e("MainActivity", "a home")
                    }
                    NavigationRoot(navController, auth, db)

                }
            }
        }
    }

}

