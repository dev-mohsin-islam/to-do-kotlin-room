package com.example.todolist.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DeleteConfirmationDialog(val callback: () -> Unit) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete Todo")
        builder.setMessage("Are you sure you want to delete this todo?")
        builder.setPositiveButton("Yes") { dialog, which ->
            callback()
        }
        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }
        return builder.create()

                //delete
    }
}


