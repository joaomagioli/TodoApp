package com.example.android.todo_app.repository.local.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.android.todo_app.repository.model.TaskModel

/**
 * Interface responsable for interacting with the entity
 */
@Dao
interface TaskDao {

    @Query("SELECT * FROM task ORDER BY priority")
    fun loadAllTasks(): LiveData<List<TaskModel>>

    @Query("SELECT * FROM task WHERE id = :id")
    fun loadTaskById(id: Long): LiveData<TaskModel>

    @Delete
    fun deleteTask(taskModel: TaskModel)

    @Insert
    fun insertTask(taskModel: TaskModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTask(taskModel: TaskModel)

}