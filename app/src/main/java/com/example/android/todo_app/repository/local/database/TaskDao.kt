package com.example.android.todo_app.repository.local.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.android.todo_app.repository.model.TaskModel

/**
 * Interface responsable for interacting with the entity
 */
@Dao
interface TaskDao {

    @Query("SELECT * FROM task ORDER BY priority")
    fun loadAllTasks(): LiveData<List<TaskModel>>

    @Delete
    fun deleteTask(taskModel: TaskModel)

    @Insert
    fun insertTask(taskModel: TaskModel)

}