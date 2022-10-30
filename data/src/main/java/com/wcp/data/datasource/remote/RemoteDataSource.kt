package com.wcp.data.datasource.remote

import com.wcp.domain.Resource
import com.wcp.domain.model.ToDoModel
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    val toDos: Flow<Resource<List<ToDoModel>>>
}