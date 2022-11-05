package com.wcp.domain.usecase

import com.wcp.domain.repository.ToDosRepository
import javax.inject.Inject

class FetchToDos @Inject constructor(private val repository: ToDosRepository) {
    suspend operator fun invoke(forceUpdate:Boolean) = repository.fetchToDos(forceUpdate)
}