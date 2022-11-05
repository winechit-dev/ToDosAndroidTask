package com.wcp.data.datasource.remote

import com.wcp.data.service.ToDoService
import com.wcp.domain.Resource
import com.wcp.domain.exception.DataException
import com.wcp.domain.model.ToDoModel
import javax.inject.Inject

class RemoteRemoteDataSourceImpl @Inject constructor(
    private val service: ToDoService
) : RemoteDataSource {
    override suspend fun getToDos(): Resource<List<ToDoModel>> {
        return try {
            Resource.Success(service.getToDos())
        } catch (e: DataException) {
            Resource.Error(e)
        }
    }
}