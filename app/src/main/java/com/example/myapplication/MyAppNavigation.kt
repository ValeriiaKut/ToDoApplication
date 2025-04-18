package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.utils.Routes
import com.example.myapplication.viewmodel.AuthViewModel
import com.example.myapplication.viewmodel.TodoViewModel
import com.example.myapplication.views.EditTodoScreen
import com.example.myapplication.views.LoginPage
import com.example.myapplication.views.RegisterPage
import com.example.myapplication.views.TodoListPage

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel){
    val navController = rememberNavController()
    val todoViewModel: TodoViewModel = viewModel()


    NavHost(
        navController = navController,
        startDestination = Routes.loginPage,
        builder = {
            composable(Routes.loginPage) {
                LoginPage(navController,modifier,authViewModel)
            }
            composable(Routes.registerPage) {
                RegisterPage(navController,modifier,authViewModel)
            }

            composable(Routes.toDoListPage){
                TodoListPage(todoViewModel, navController,modifier,authViewModel)
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
