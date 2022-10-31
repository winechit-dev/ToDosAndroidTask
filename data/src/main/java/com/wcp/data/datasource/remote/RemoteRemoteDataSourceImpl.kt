package com.wcp.data.datasource.remote

import com.wcp.data.service.ToDoService
import com.wcp.domain.model.ToDoModel
import javax.inject.Inject

class RemoteRemoteDataSourceImpl @Inject constructor(
    private val service: ToDoService
) : RemoteDataSource {
    override suspend fun getToDos(): List<ToDoModel> {
        return service.getToDos()
    }
}