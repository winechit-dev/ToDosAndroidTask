package com.wcp.domain.model

data class ToDoModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)
