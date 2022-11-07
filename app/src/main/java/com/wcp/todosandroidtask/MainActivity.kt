package com.wcp.todosandroidtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.wcp.todosandroidtask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private val todoItemAdapter: TodoItemAdapter by lazy {
        TodoItemAdapter {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        collectToDos()
    }

    private fun initUI() {
        with(binding) {
            rvTodos.layoutManager = LinearLayoutManager(this@MainActivity)
            rvTodos.adapter = todoItemAdapter

            swipeRefresh.setOnRefreshListener {
                viewModel.fetchTodos(true)
            }
            viewModel.fetchTodos(false)
        }
    }

    private fun collectToDos() {

        // Loading State
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.mapNotNull { it.isLoading }.collectLatest {
                   binding.swipeRefresh.isRefreshing = it
                }
            }
        }

        // Data State
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.mapNotNull { it.data }.collectLatest {
                    binding.tvCount.text = it.size.toString()
                    todoItemAdapter.submitList(it)
                }
            }
        }

        // Error State
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.mapNotNull { it.throwable }.collectLatest {
                    MaterialAlertDialogBuilder(this@MainActivity)
                        .setMessage(it.message.toString())
                        .setPositiveButton("Ok") { _, _ ->
                            viewModel.userMessageShown()
                        }.show()
                }
            }
        }
    }
}