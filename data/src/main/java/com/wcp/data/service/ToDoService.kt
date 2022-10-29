package com.wcp.data.service

import com.wcp.domain.model.ToDoModel
import retrofit2.http.GET

interface ToDoService {
    @GET("todos")
    suspend fun getToDos(): List<ToDoModel>
}