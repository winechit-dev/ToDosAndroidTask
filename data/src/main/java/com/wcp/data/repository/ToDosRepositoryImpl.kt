package com.wcp.data.repository

import com.wcp.data.datasource.RemoteDataSource
import com.wcp.domain.exception.DataException
import com.wcp.domain.model.ToDoModel
import com.wcp.domain.repository.ToDosRepository
import com.wcp.domain.type.Either
import javax.inject.Inject

class ToDosRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : ToDosRepository {
    override suspend fun getToDos(): Either<DataException, List<ToDoModel>> {
        return remoteDataSource.getToDos()
    }
}