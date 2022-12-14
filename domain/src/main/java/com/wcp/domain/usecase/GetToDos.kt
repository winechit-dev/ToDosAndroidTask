package com.wcp.domain.usecase

import com.wcp.domain.repository.ToDosRepository
import javax.inject.Inject

class GetToDos @Inject constructor(private val repository: ToDosRepository) {
    operator fun invoke() = repository.toDos
}