package com.example.todolist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.databinding.TodoItemRowBinding
import com.example.todolist.entities.TodoModel
import getFormattedDateTime
import priority_high
import priority_low
import priority_medium
import todo_delete
import todo_edit

class TodoAdapter(val actionCallback: (TodoModel, String) ->Unit) : ListAdapter<TodoModel, TodoAdapter.TodoViewHolder>(TodoDiffCallback()){
    class TodoViewHolder(private val binding: TodoItemRowBinding, val actionCallback: (TodoModel, String) ->Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todoModel: TodoModel){
            binding.todo = todoModel
            binding.rowTodoCompleteCB.isChecked = todoModel.isCompleted

            binding.rowTodoCompleteCB.setOnClickListener(){
                 actionCallback(todoModel, todo_edit)
            }
            binding.rowTodoMenuIcon.setOnClickListener(){
                val popupMenu = PopupMenu(it.context, it)
                val inflater = popupMenu.menuInflater;
                 inflater.inflate(R.menu.todo_row_menu, popupMenu.menu)
                popupMenu.show()
                popupMenu.setOnMenuItemClickListener { item ->
                    when(item.itemId){

                        R.id.item_delete -> {
                            actionCallback(todoModel, todo_delete)
                            true
                        }
                        else -> false
                    }


                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = TodoItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding, actionCallback)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todoModel = getItem(position)
        holder.bind(todoModel)
    }
}

class TodoDiffCallback : DiffUtil.ItemCallback<TodoModel>(){
    override fun areItemsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
       return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
        return oldItem == newItem
    }
}