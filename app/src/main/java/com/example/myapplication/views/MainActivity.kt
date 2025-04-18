package com.example.myapplication.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.utils.Routes
import com.example.myapplication.viewmodel.TodoViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]

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

                    composable(Routes.toDoListPage){
                        TodoListPage(todoViewModel, navController)
                    }
                    composable(
                        route = "editTodo/{todoId}",
                        arguments = listOf(navArgument("todoId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val todoId = backStackEntry.arguments?.getInt("todoId") ?: return@composable
                        EditTodoScreen(viewModel = todoViewModel, todoId = todoId, navController = navController)
                    }

                }
            )
        }
    }
}