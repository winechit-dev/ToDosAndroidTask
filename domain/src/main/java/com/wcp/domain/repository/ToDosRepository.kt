package com.wcp.domain.repository

import com.wcp.domain.Resource
import com.wcp.domain.model.ToDoModel
import kotlinx.coroutines.flow.Flow

interface ToDosRepository {
    suspend fun fetchToDos(forceUpdate: Boolean): Resource<Boolean>
    val toDos: Flow<Resource<List<ToDoModel>>>
}