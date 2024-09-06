package com.example.todolist.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class TimePickerDialogFragment(val callback: (Long) -> Unit) : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return TimePickerDialog(requireActivity(), this, hour, minute, false)
    }
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
         val calendar = Calendar.getInstance()
        calendar.set(0, 0, 0, hourOfDay, minute)
        val time = calendar.timeInMillis
        callback(time)
    }
}