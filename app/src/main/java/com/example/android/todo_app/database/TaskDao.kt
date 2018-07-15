package com.example.android.todo_app.database

import android.arch.persistence.room.*

/**
 * Interface responsable for interacting with the entity
 */
@Dao
interface TaskDao {

    @Query("SELECT * FROM task ORDER BY priority")
    fun loadAllTasks() : List<TaskModel>

    @Query("SELECT * FROM task WHERE id = :id")
    fun loadTaskById(id: Long) : TaskModel

    @Delete
    fun deleteTask(taskModel: TaskModel)

    @Insert
    fun insertTask(taskModel: TaskModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTask(taskModel: TaskModel)

}