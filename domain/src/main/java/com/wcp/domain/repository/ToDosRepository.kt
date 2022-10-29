package com.wcp.domain.repository

import com.wcp.domain.exception.DataException
import com.wcp.domain.model.ToDoModel
import com.wcp.domain.type.Either

interface ToDosRepository {
    suspend fun getToDos(): Either<DataException, List<ToDoModel>>
}