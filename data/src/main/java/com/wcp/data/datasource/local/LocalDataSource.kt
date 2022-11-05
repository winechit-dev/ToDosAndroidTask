package com.wcp.data.datasource.local

import com.wcp.domain.Resource
import com.wcp.domain.model.ToDoModel
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getTodosStream(): Flow<Resource<List<ToDoModel>>>
    suspend fun getToDos(): List<ToDoModel>
    suspend fun saveToDos(entities: List<ToDoModel>)
    suspend fun clearToDos()
}