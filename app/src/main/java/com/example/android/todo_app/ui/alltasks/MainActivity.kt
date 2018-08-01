package com.example.android.todo_app.ui.alltasks

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.example.android.todo_app.R
import com.example.android.todo_app.adapter.TaskAdapter
import com.example.android.todo_app.database.Database
import com.example.android.todo_app.database.TaskDao
import com.example.android.todo_app.ui.addtask.AddTaskActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var taskDao: TaskDao
    private lateinit var taskAdapter: TaskAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()

        addProduct()
        configRecyclerView()

        onSwipeItem()

    }

    private fun setupViewModel() {
        val factory = MainViewModelFactory(application)
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        viewModel.getTasks().observe(this, Observer { tasks ->
            tasks?.let { taskAdapter.add(it) }
        })
    }

    private fun addProduct() {
        fab.setOnClickListener { _ ->
            val addTaskIntent = Intent(this, AddTaskActivity::class.java)
            startActivity(addTaskIntent)
        }
    }

    private fun configRecyclerView() {
        taskAdapter = TaskAdapter(this)
        recycler_view_task_list.adapter = taskAdapter
        recycler_view_task_list.layoutManager = LinearLayoutManager(this)
    }

    private fun onSwipeItem() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView?,
                                viewHolder: RecyclerView.ViewHolder?,
                                target: RecyclerView.ViewHolder?): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val tasks = taskAdapter.tasks
                val database = Database.getInstance(context = this@MainActivity)
                taskDao = database.taskDao()
                taskDao.deleteTask(tasks[position])
            }

        }).attachToRecyclerView(recycler_view_task_list)
    }

}
