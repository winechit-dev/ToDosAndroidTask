package com.wcp.todosandroidtask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wcp.domain.model.ToDoModel
import com.wcp.todosandroidtask.databinding.ItemTodoBinding

class TodoItemAdapter(private val onItemClickListener: (ToDoModel) -> Unit) :
    ListAdapter<ToDoModel, TodoItemAdapter.PopularMovieViewHolder>(DiffCallBack) {

    companion object DiffCallBack : DiffUtil.ItemCallback<ToDoModel>() {

        override fun areItemsTheSame(oldItem: ToDoModel, newItem: ToDoModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ToDoModel, newItem: ToDoModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        return PopularMovieViewHolder(ItemTodoBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        val movieItemPosition: ToDoModel = getItem(position)
        holder.bind(movieItemPosition, onItemClickListener)
    }

    class PopularMovieViewHolder(private var binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(toDoModel: ToDoModel, onItemClickListener: (ToDoModel) -> Unit) {

            val imageResource = if (toDoModel.completed)
                R.drawable.ic_check_box_24 else
                R.drawable.ic_check_box_outline_24

            binding.ivCheckbox.setImageResource(imageResource)

            binding.tvTitle.text = toDoModel.title
        }
    }
}