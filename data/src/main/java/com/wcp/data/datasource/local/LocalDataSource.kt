package com.wcp.data.datasource.local

import com.wcp.domain.model.ToDoModel

interface LocalDataSource {
    suspend fun getToDos(): List<ToDoModel>
    suspend fun saveToDos(entities: List<ToDoModel>)
    suspend fun clearToDos()
}