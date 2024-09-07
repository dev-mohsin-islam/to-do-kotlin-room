package com.example.todolist.bindingadapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.todolist.R
import getFormattedDateTime
import priority_low
import priority_medium


@BindingAdapter("app:setPriorityIcon")
fun setPriorityIcon(iv: ImageView, priority: String) {
    val iconResource = when(priority){
        priority_low -> R.drawable.baseline_purplestar_24
        priority_medium -> R.drawable.baseline_greenstar_24
        else -> R.drawable.ic_baseline_redstar_24
    }
    iv.setImageResource(iconResource)
}

@BindingAdapter("app:setFormatedDate")
fun setFormatedDate(tv: TextView, date: Long){
    tv.text = getFormattedDateTime(date, "dd/MM/yyyy")
}

@BindingAdapter("app:setFormatedTime")
fun setFormatedTime(tv: TextView, date: Long){
    tv.text = getFormattedDateTime(date, "hh:mm a")
}