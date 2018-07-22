package com.example.android.todo_app

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.android.todo_app.database.TaskModel
import kotlinx.android.synthetic.main.task_list.view.*
import java.text.SimpleDateFormat
import java.util.*

class TaskAdapter(private val mContext: Context,
                  private val mTasks: MutableList<TaskModel> = mutableListOf()) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private val dateFormat: SimpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.task_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mTasks.size


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val task = mTasks[position]

        holder?.let {
            it.taskDescription.text = task.description
            it.taskDate.text = dateFormat.format(task.updatedAt)
            it.taskPriority.text = task.priority.toString()

            // Setting the background color
            it.taskPriority.background
            val priorityColor = getPriorityColor(task.priority)
            it.taskPriority.setBackgroundColor(priorityColor)
        }

    }

    private fun getPriorityColor(priority: Int): Int {
        var priorityColor = 0

        when (priority) {
            1 -> priorityColor = ContextCompat.getColor(mContext, R.color.materialRed)
            2 -> priorityColor = ContextCompat.getColor(mContext, R.color.materialOrange)
            3 -> priorityColor = ContextCompat.getColor(mContext, R.color.materialYellow)
        }

        return priorityColor
    }

    fun replaceAllTasks(tasks : List<TaskModel>) {
        mTasks.clear()
        mTasks.addAll(tasks)
        notifyDataSetChanged()
    }

    fun add(task : List<TaskModel>) {
        mTasks.addAll(task)
        notifyItemRangeInserted(0, mTasks.size)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val taskDescription : TextView = itemView.text_view_task_description
        val taskDate : TextView = itemView.text_view_task_date
        val taskPriority : TextView = itemView.text_view_task_priority

    }

    companion object {
        private const val DATE_FORMAT = "dd/MM/yyy"
    }
}