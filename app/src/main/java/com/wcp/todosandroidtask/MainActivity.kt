package com.wcp.todosandroidtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
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
        }
    }

    private fun collectToDos() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.mapNotNull { it.isLoading }.collectLatest {
                    binding.progressBar.isVisible = it
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.mapNotNull { it.data }.collectLatest {
                    todoItemAdapter.submitList(it)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.mapNotNull { it.throwable }.collectLatest {
                    Toast.makeText(this@MainActivity, it.message.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}