package com.example.myapplication.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.MainApplication
import com.example.myapplication.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Date
import java.time.Instant

class TodoViewModel : ViewModel() {

    val todoDao = MainApplication.todoDatebase.getTodoDao()

    val todoList : LiveData<List<Todo>> = todoDao.getAllTodo()

    @RequiresApi(Build.VERSION_CODES.O)
    fun addTodo(title: String){
        viewModelScope.launch(Dispatchers.IO){
            todoDao.addTodo(Todo(title = title, createdAt = Date.from(Instant.now())))
        }
    }

    fun deleteTodo(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            todoDao.deleteTodo(id)
        }
    }

}