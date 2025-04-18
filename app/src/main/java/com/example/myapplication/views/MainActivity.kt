package com.example.myapplication.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.myapplication.MyAppNavigation
import com.example.myapplication.ui.theme.FirebaseAuthTheme
import com.example.myapplication.viewmodel.AuthViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val authViewModel:AuthViewModel by viewModels()
        setContent {
            FirebaseAuthTheme{
             Scaffold {  innerPadding ->
                 MyAppNavigation(
                     modifier = Modifier.padding(innerPadding),
                     authViewModel = authViewModel
                 )
             }
         }
        }
    }
}