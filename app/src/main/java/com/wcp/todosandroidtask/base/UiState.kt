package com.wcp.todosandroidtask.base

data class UiState<T>(
    val isLoading: Boolean = true,
    val throwable: Throwable? = null,
    val data: T? = null
)
