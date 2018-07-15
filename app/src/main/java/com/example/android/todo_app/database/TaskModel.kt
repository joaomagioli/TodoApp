package com.example.android.todo_app.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Model of the task
 */
@Entity(tableName = "task")
class TaskModel(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val description: String,
        val priority: Int,
        @ColumnInfo(name = "updated_at")
        val updatedAt: Date)