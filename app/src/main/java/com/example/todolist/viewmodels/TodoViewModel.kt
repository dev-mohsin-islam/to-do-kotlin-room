package com.example.todolist.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.entities.TodoModel
import com.example.todolist.repos.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) :AndroidViewModel(application) {
    private var repository = TodoRepository(application)
    init {
        repository = TodoRepository(application)
    }
    fun insertTodo(todoModel: TodoModel){
      viewModelScope.launch {
          repository.insertTodo(todoModel)
      }
    }

    fun getAllTodos() : LiveData<List<TodoModel>> {
        return repository.getAllTodos()
    }

    fun updateTodo(todoModel: TodoModel) {
        todoModel.isCompleted = !todoModel.isCompleted
        viewModelScope.launch {
            repository.updateTodo(todoModel)
        }
    }

    fun deleteTodo(todoModel: TodoModel) {
        viewModelScope.launch {
            repository.deleteTodo(todoModel)
        }
    }

}