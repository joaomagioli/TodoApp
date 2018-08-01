package com.example.android.todo_app.ui.alltasks

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.example.android.todo_app.database.Database
import com.example.android.todo_app.database.TaskDao
import com.example.android.todo_app.model.TaskModel

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var tasks : LiveData<List<TaskModel>>
    private val taskDao: TaskDao

    init {
        val database = Database.getInstance(application)
        taskDao = database.taskDao()
        tasks = taskDao.loadAllTasks()
    }

    fun getTasks() : LiveData<List<TaskModel>> = tasks
}