package com.example.android.todo_app.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.brazilianDateFormat() : String {
    val datePattern = "dd/MM/yyy"
    val format = SimpleDateFormat(datePattern, Locale.getDefault())
    val dateFormat = format.format(this.time)
    return dateFormat
}