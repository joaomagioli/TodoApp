package com.example.android.todo_app.ui.addtask

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import com.example.android.todo_app.R
import com.example.android.todo_app.database.Database
import com.example.android.todo_app.model.TaskModel
import kotlinx.android.synthetic.main.content_add_task.*
import java.util.*

class AddTaskActivity : AppCompatActivity() {

    companion object {
        private const val HIGH_PRIORITY_VALUE = 1
        private const val MEDIUM_PRIORITY_VALUE = 2
        private const val LOW_PRIORITY_VALUE = 3
        private const val EDIT_TEXT_ERROR_MESSAGE = "The task name is required"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        buttonAddTask.setOnClickListener {
            addTask()
        }
    }

    private fun addTask() {
        val taskDescription = editTextAddTask.text.toString()
        val taskPriority = getTaskPriority()
        val date = Date()

        val database = Database.getInstance(this)
        val taskDao = database.taskDao()

        taskDao.insertTask(TaskModel(description = taskDescription, priority = taskPriority, updatedAt = date))

        validateTextAndFinishActivity(editTextAddTask)
    }

    private fun getTaskPriority(): Int {
        val checkedId = radioGroupPriority.checkedRadioButtonId

        return when (checkedId) {
            R.id.radioButtonLowPriority -> LOW_PRIORITY_VALUE
            R.id.radioButtonMediumPriority -> MEDIUM_PRIORITY_VALUE
            R.id.radioButtonHighPriority -> HIGH_PRIORITY_VALUE
            else -> LOW_PRIORITY_VALUE
        }
    }

    private fun validateTextAndFinishActivity(editText: EditText) {

        if (editText.text.isEmpty()) {
            editText.setError(EDIT_TEXT_ERROR_MESSAGE, ContextCompat.getDrawable(this, R.drawable.notification_icon_background))
        } else {
            finish()
        }
    }
}