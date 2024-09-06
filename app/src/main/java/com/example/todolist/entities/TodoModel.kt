package com.example.todolist.entities

data  class TodoModel(
    val id: Long = 0,
    var name: String,
    var date: Long,
    var time: Long,
    val priority: String = "",
    var isCompleted: Boolean = false
)