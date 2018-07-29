package com.example.android.todo_app.database

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * Date converter for the room library
 */
class DateConverter {

    @TypeConverter
    fun toDate(timeStamp: Long?): Date? = if (timeStamp == null) null else Date(timeStamp)

    @TypeConverter
    fun toTimeStamp(date: Date?): Long? = date?.time
}