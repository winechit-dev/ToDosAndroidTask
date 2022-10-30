package com.wcp.data.datasource.remote

import com.wcp.data.extensions.convertEither
import com.wcp.data.service.ToDoService
import com.wcp.domain.exception.DataException
import com.wcp.domain.model.ToDoModel
import com.wcp.domain.type.Either
import javax.inject.Inject

class RemoteRemoteDataSourceImpl @Inject constructor(
    private val service: ToDoService
) : RemoteDataSource {

    override suspend fun getToDos(): Either<DataException, List<ToDoModel>> {
        return try {
            val todos = service.getToDos()
            Either.Right(todos)
        } catch (e: DataException) {
            e.convertEither()
        }
    }
}