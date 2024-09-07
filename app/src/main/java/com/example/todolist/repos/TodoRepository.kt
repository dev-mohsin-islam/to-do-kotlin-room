package com.example.todolist.repos

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.todolist.daos.TodoDao
import com.example.todolist.db.ToDoDataBase
import com.example.todolist.entities.TodoModel

class TodoRepository(val context : Context) {
    private val todoDao: TodoDao
    init {
        todoDao = ToDoDataBase.getDB(context).getTodoDao()
    }

    suspend fun insertTodo(todoModel: TodoModel){
        todoDao.addTodo(todoModel)
    }
    suspend fun updateTodo(todoModel: TodoModel){
        todoDao.updateTodo(todoModel)
    }

    fun getAllTodos() : LiveData<List<TodoModel>> {
        return todoDao.getAllTodos()
    }

    suspend fun deleteTodo(todoModel: TodoModel) {
        todoDao.deleteTodo(todoModel)
    }

}