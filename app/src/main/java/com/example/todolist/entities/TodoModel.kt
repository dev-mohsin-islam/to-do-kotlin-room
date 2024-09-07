package com.example.todolist.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_todo")
data  class TodoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var name: String,
    var date: Long,
    var time: Long,
    val priority: String = "",
    @ColumnInfo(name = "is_completed")
    var isCompleted: Boolean = false
)