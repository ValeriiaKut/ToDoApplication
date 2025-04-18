package com.example.myapplication.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.model.Todo
import com.example.myapplication.ui.theme.PurpleGrey40
import com.example.myapplication.ui.theme.PurpleGrey80
import com.example.myapplication.utils.Routes
import com.example.myapplication.viewmodel.AuthState
import com.example.myapplication.viewmodel.AuthViewModel
import com.example.myapplication.viewmodel.TodoViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TodoListPage(
    viewModel: TodoViewModel,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val todoList by viewModel.todoList.observeAsState()
    var showDialog by remember { mutableStateOf(false) }
    var inputTitle by remember { mutableStateOf("") }
    var inputDescription by remember { mutableStateOf("") }
    val authState = authViewModel.authState.observeAsState()


    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Unauthenticated -> navController.navigate(Routes.loginPage)
            else -> Unit
        }
    }


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
            .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LogoutIcon(authViewModel = authViewModel)

            Text(
                text = stringResource(R.string.myToDoList),
                fontSize = 24.sp,
                color = Color.DarkGray,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center

            )

            Button(
                onClick = { showDialog = true },
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PurpleGrey40,
                    contentColor = Color.White
                )

            ) {
                Text(text = stringResource(id = R.string.add))
            }
        }


        todoList?.let {
            LazyColumn {
                itemsIndexed(it) { _: Int, item: Todo ->
                    TodoItem(
                        item = item,
                        onDelete = { viewModel.deleteTodo(item.id) },
                        onClick = { navController.navigate("editTodo/${item.id}") }
                    )
                }
            }
        } ?: Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.noItems),
            fontSize = 16.sp
        )

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            viewModel.addTodo(inputTitle, inputDescription)
                            inputTitle = ""
                            inputDescription = ""
                            showDialog = false

                        }
                    ) {
                        Text(stringResource(R.string.add))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text(stringResource(R.string.cancel))
                    }
                },
                title = { Text(stringResource(R.string.addNewTask)) },
                text = {
                    Column {
                        OutlinedTextField(
                            value = inputTitle,
                            onValueChange = { inputTitle = it },
                            label = { Text(stringResource(R.string.todoTitle)) },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = inputDescription,
                            onValueChange = { inputDescription = it },
                            label = { Text(stringResource(R.string.todoDescription)) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

            )
        }
    }
}

@Composable
fun LogoutIcon( authViewModel: AuthViewModel) {
    var showLogoutDialog by remember { mutableStateOf(false) }

    IconButton(onClick = { showLogoutDialog = true }) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
            contentDescription = "Logout"
        )
    }


    if (showLogoutDialog) {
        AlertDialog(
            onDismissRequest = { showLogoutDialog = false },
            title = {
                Text(stringResource(R.string.logOut))
            },
            text = {
                Text(stringResource(R.string.sure))
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showLogoutDialog = false
                        authViewModel.signOut()
                    }
                ) {
                    Text(stringResource(R.string.logOut))
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutDialog = false }) {
                    Text(stringResource(R.string.cancel))
                }
            }
        )
    }
}

@Composable
fun DeleteTodoIcon(onDelete: () -> Unit) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    IconButton(onClick = { showDeleteDialog = true }) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_delete_24),
            contentDescription = "Delete",
            tint = Color.Gray
        )
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = {
                Text(stringResource(R.string.delete))
            },
            text = {
                Text(stringResource(R.string.sure2))
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        onDelete()
                    }
                ) {
                    Text(stringResource(R.string.delete))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text(stringResource(R.string.cancel))
                }
            }
        )
    }
}


@Composable
fun TodoItem(item: Todo, onDelete: () -> Unit, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 6.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(12.dp))
            .background(PurpleGrey80)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = SimpleDateFormat("HH:mm, dd/MM", Locale.ENGLISH).format(item.createdAt),
                fontSize = 12.sp,
                color = Color.Gray
            )
            Text(
                text = item.title,
                fontSize = 20.sp,
                color = Color.Black
            )
            Text(
                text = item.description,
                fontSize = 18.sp,
                color = Color.Black
            )
        }
        DeleteTodoIcon(onDelete = onDelete)

    }
}
