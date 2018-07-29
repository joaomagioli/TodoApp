package com.example.android.todo_app.ui.addtask

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.todo_app.R
import com.example.android.todo_app.database.Database
import com.example.android.todo_app.model.TaskModel
import kotlinx.android.synthetic.main.content_add_task.*
import java.util.*

class AddTaskActivity : AppCompatActivity() {

    companion object {
        const val HIGH_PRIORITY_VALUE = 1
        const val MEDIUM_PRIORITY_VALUE = 2
        const val LOW_PRIORITY_VALUE = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        buttonAddTask.setOnClickListener {
            addTask()
        }
    }

    private fun addTask() {
        val taskDescription: String = editTextAddTask.text.toString()
        val taskPriority = getTaskPriority()
        val date = Date()

        val database = Database.getInstance(this)
        val taskDao = database.taskDao()

        taskDao.insertTask(TaskModel(description = taskDescription, priority = taskPriority, updatedAt = date))

        finish()
    }

    private fun getTaskPriority(): Int {
        val checkedId = radioGroupPriority.checkedRadioButtonId

        val priority = when (checkedId) {
            R.id.radioButtonLowPriority -> LOW_PRIORITY_VALUE
            R.id.radioButtonMediumPriority -> MEDIUM_PRIORITY_VALUE
            R.id.radioButtonHighPriority -> HIGH_PRIORITY_VALUE
            else -> LOW_PRIORITY_VALUE
        }
        return priority
    }
}