package com.example.android.todo_app.view.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.android.todo_app.R
import com.example.android.todo_app.extensions.brazilianDateFormat
import com.example.android.todo_app.repository.model.TaskModel
import kotlinx.android.synthetic.main.task_list.view.*

class TaskAdapter(private val mContext: Context,
                  private val mTasks: MutableList<TaskModel> = mutableListOf()) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    companion object {
        private const val HIGH_PRIORITY_VALUE = 1
        private const val MEDIUM_PRIORITY_VALUE = 2
        private const val LOW_PRIORITY_VALUE = 3
    }

    val tasks get() = mTasks

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.task_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mTasks.size


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val task = mTasks[position]

        holder?.let {
            it.taskDescription.text = task.description
            it.taskDate.text = task.updatedAt.brazilianDateFormat()
            it.taskPriority.text = task.priority.toString()

            // Setting the background color
            it.taskPriority.background
            val priorityColor = getPriorityColor(task.priority)
            it.taskPriority.setBackgroundColor(priorityColor)
        }

    }

    fun add(task: List<TaskModel>) {
        mTasks.clear()
        mTasks.addAll(task)
        notifyDataSetChanged()
    }

    private fun getPriorityColor(priority: Int): Int {
        var priorityColor = 0

        when (priority) {
            LOW_PRIORITY_VALUE -> priorityColor = ContextCompat.getColor(mContext, R.color.materialYellow)
            MEDIUM_PRIORITY_VALUE -> priorityColor = ContextCompat.getColor(mContext, R.color.materialOrange)
            HIGH_PRIORITY_VALUE -> priorityColor = ContextCompat.getColor(mContext, R.color.materialRed)
        }

        return priorityColor
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val taskDescription: TextView = itemView.text_view_task_description
        val taskDate: TextView = itemView.text_view_task_date
        val taskPriority: TextView = itemView.text_view_task_priority

    }
}