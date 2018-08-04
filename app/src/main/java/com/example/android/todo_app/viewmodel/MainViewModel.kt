package com.example.android.todo_app.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.example.android.todo_app.repository.local.database.Database
import com.example.android.todo_app.repository.local.database.TaskDao
import com.example.android.todo_app.repository.model.TaskModel
import kotlinx.coroutines.experimental.asCoroutineDispatcher
import kotlinx.coroutines.experimental.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val taskDao: TaskDao

    init {
        val database = Database.getInstance(application)
        taskDao = database.taskDao()
    }

    fun getTasks() : LiveData<List<TaskModel>> {
        return taskDao.loadAllTasks()
    }

    fun deleteTask(taskModel: TaskModel) {
        launch(AsyncTask.THREAD_POOL_EXECUTOR.asCoroutineDispatcher()) {
            taskDao.deleteTask(taskModel)
        }
    }
}