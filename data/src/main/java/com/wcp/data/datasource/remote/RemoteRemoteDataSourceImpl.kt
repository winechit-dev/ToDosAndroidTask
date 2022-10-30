package com.wcp.data.datasource.remote

import com.wcp.data.extensions.convertException
import com.wcp.data.service.ToDoService
import com.wcp.domain.Resource
import com.wcp.domain.model.ToDoModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteRemoteDataSourceImpl @Inject constructor(
    private val service: ToDoService
) : RemoteDataSource {

    override val toDos: Flow<Resource<List<ToDoModel>>>
        get() = flow {
            try {
                val todos = service.getToDos()
                emit(Resource.Success(todos))
            } catch (e: Exception) {
                emit(Resource.Error(e.convertException()))
            }
        }
}