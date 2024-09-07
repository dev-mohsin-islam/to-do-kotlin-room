package com.example.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.adapters.TodoAdapter
import com.example.todolist.databinding.FragmentToDoListBinding
import com.example.todolist.db.ToDoDataBase
import com.example.todolist.dialogs.DeleteConfirmationDialog
import com.example.todolist.entities.TodoModel
import com.example.todolist.viewmodels.TodoViewModel
import todo_delete
import todo_edit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ToDoListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ToDoListFragment : Fragment() {
    private lateinit var binding: FragmentToDoListBinding
    private val todoViewModel: TodoViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentToDoListBinding.inflate(inflater, container, false)
       val adapter = TodoAdapter(::todoAction)
        binding.todoRV.layoutManager = LinearLayoutManager(activity)
        binding.todoRV.adapter = adapter
        todoViewModel.getAllTodos().observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        binding.todoCreateBTN.setOnClickListener{
           findNavController().navigate(R.id.newTodoAction)
        }

        return binding.root
    }

    private fun todoAction(todoModel: TodoModel, tag: String){
        if(tag == todo_edit){
            todoViewModel.updateTodo(todoModel)
        }else if(tag == todo_delete){
            DeleteConfirmationDialog {
                todoViewModel.deleteTodo(todoModel)
            }.show(parentFragmentManager, "delete_dialog")
        }

    }

}