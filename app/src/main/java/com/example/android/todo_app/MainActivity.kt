package com.example.android.todo_app

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.android.todo_app.adapter.TaskAdapter
import com.example.android.todo_app.database.Database
import com.example.android.todo_app.database.TaskDao
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var taskDao: TaskDao
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Database.getInstance(this)
        taskDao = database.taskDao()
        val tasksLiveData = taskDao.loadAllTasks()
        tasksLiveData.observe(this, Observer {
            tasks -> tasks?.let { taskAdapter.replaceAllTasks(it)}
        })

        addProduct()
        configRecyclerView()

    }

    private fun addProduct() {
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    private fun configRecyclerView() {
        taskAdapter = TaskAdapter(this)
        recycler_view_task_list.adapter = taskAdapter
        recycler_view_task_list.layoutManager = LinearLayoutManager(this)
    }

}
