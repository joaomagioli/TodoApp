package com.example.android.todo_app.ui.addtask

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.os.AsyncTask
import com.example.android.todo_app.database.Database
import com.example.android.todo_app.database.TaskDao
import com.example.android.todo_app.model.TaskModel
import kotlinx.coroutines.experimental.asCoroutineDispatcher
import kotlinx.coroutines.experimental.launch

class AddTaskViewModel(application: Application) : AndroidViewModel(application) {

    private val taskDao : TaskDao

    init {
        val database = Database.getInstance(application)
        taskDao = database.taskDao()
    }

    fun insertTask(taskModel: TaskModel) {
        launch(AsyncTask.THREAD_POOL_EXECUTOR.asCoroutineDispatcher()) {
            taskDao.insertTask(taskModel)
        }
    }
}