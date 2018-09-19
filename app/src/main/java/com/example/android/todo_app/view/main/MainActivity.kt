package com.example.android.todo_app.view.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.example.android.todo_app.R
import com.example.android.todo_app.view.adapter.TaskAdapter
import com.example.android.todo_app.view.addtask.AddTaskActivity
import com.example.android.todo_app.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var taskAdapter: TaskAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getTasksFromViewModel()

        callAddProductActivity()

        configRecyclerView()

    }

    private fun getTasksFromViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getTasks().observe(this, Observer { tasks ->
            tasks?.let { taskAdapter.add(it) }
        })
    }

    private fun callAddProductActivity() {
        fab.setOnClickListener { _ ->
            startActivity(Intent(this, AddTaskActivity::class.java))
        }
    }

    private fun configRecyclerView() {
        taskAdapter = TaskAdapter(this)

        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val tasks = taskAdapter.tasks
                viewModel.deleteTask(tasks[position])
            }
        }

        with(recycler_view_task_list) {
            adapter = taskAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(SimpleDividerItemDecoration(this@MainActivity))
        }

        ItemTouchHelper(swipeHandler).apply {
            attachToRecyclerView(recycler_view_task_list)
        }

    }

}
