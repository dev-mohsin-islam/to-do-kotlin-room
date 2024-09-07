package com.example.todolist

import android.nfc.Tag
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.databinding.FragmentNewToDoBinding
import com.example.todolist.dialogs.DatePickerDialogFragment
import com.example.todolist.dialogs.TimePickerDialogFragment
import com.example.todolist.entities.TodoModel
import com.example.todolist.viewmodels.TodoViewModel
import getFormattedDateTime
import priority_medium
import todo_edit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewToDoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewToDoFragment : Fragment() {
    private lateinit var binding: FragmentNewToDoBinding
    private  var priority = priority_medium
    private var dateTimeInMillis = System.currentTimeMillis()
    private var timeInMillis = System.currentTimeMillis()
    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewToDoBinding.inflate(inflater, container, false)
        binding.priorityRG.setOnCheckedChangeListener { radioGroup, checkedId ->
            val rb = radioGroup.findViewById<RadioButton>(checkedId)
            priority = rb.text.toString()
            Toast.makeText(activity, priority, Toast.LENGTH_SHORT).show()
        }
        binding.dateBTN.setOnClickListener(){
            DatePickerDialogFragment { timeStamp ->
                dateTimeInMillis = timeStamp
                binding.dateBTN.text = getFormattedDateTime(timeStamp, "dd/MM/yyyy")

            }.show(childFragmentManager, "DATE_PICKER")
        }

        binding.timeBTN.setOnClickListener() {
            TimePickerDialogFragment { timeStamp ->
                timeInMillis = timeStamp
                binding.timeBTN.text = getFormattedDateTime(timeStamp, "hh:mm a")
            }.show(childFragmentManager, "TIME_PICKER")
        }

        binding.saveBTN.setOnClickListener(){
            val toDoName = binding.newTodo.text.toString()
            if(toDoName.isEmpty()){
                binding.newTodo.error = "Please enter a name"
                binding.newTodo.requestFocus()
                return@setOnClickListener
            }

            val todo = TodoModel(
                name = toDoName,
                date = dateTimeInMillis,
                time = timeInMillis,
                priority = priority
            )
            todoViewModel.insertTodo(todo)
            findNavController().navigate(R.id.backTodoListAction)
        }
        return binding.root
    }



}