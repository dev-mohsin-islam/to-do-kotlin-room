package com.example.todolist.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todolist.entities.TodoModel

@Dao
interface TodoDao {
    @Insert
    suspend fun addTodo(todo: TodoModel)

    @Update
    suspend fun updateTodo(todo: TodoModel)

    @Delete
    suspend fun deleteTodo(todo: TodoModel)

    @Query("SELECT * FROM tbl_todo order by date desc")
    fun getAllTodos(): LiveData<List<TodoModel>>
}