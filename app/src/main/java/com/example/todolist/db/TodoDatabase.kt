package com.example.todolist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.daos.TodoDao
import com.example.todolist.entities.TodoModel

@Database(entities = [TodoModel::class], version = 1)
abstract class ToDoDataBase : RoomDatabase() {
     abstract fun getTodoDao(): TodoDao
     
    companion object{
        private var todoDatabase: ToDoDataBase? = null

        fun getDB(context: Context): ToDoDataBase {
            return todoDatabase ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDataBase::class.java,
                    "todo_database"
                )
                    .allowMainThreadQueries().build()
                todoDatabase = instance
                instance
            }
        }
    }
}