package com.example.myapplication

import com.example.myapplication.views.HomePage
import com.example.myapplication.views.LoginPage
import com.example.myapplication.views.RegisterPage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.utils.Routes



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Routes.loginPage,
                builder = {
                    composable(Routes.loginPage) {
                        LoginPage(navController)
                    }
                    composable(Routes.registerPage) {
                        RegisterPage(navController)
                    }
                    composable(Routes.homePage){
                        HomePage()
                    }

                }
            )
        }
    }
}