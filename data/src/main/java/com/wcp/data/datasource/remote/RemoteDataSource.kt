package com.wcp.data.datasource.remote

import com.wcp.domain.Resource
import com.wcp.domain.model.ToDoModel

interface RemoteDataSource {
    suspend fun getToDos(): Resource<List<ToDoModel>>
}