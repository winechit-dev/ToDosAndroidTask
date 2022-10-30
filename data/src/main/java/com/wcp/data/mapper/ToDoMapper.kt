package com.wcp.data.mapper

import com.wcp.data.database.entity.ToDoEntity
import com.wcp.domain.model.ToDoModel
import javax.inject.Inject

class ToDoMapper @Inject constructor() {
    fun mapToDoModels(entities: List<ToDoEntity>): List<ToDoModel> {
        return entities.map { mapToDoModel(it) }
    }

    fun mapToDoEntities(toDos: List<ToDoModel>): List<ToDoEntity> {
        return toDos.map { mapToDoEntity(it) }
    }

    private fun mapToDoModel(entity: ToDoEntity) = ToDoModel(
        id = entity.id,
        userId = entity.userId,
        title = entity.title,
        completed = entity.completed
    )

    private fun mapToDoEntity(model: ToDoModel) = ToDoEntity(
        id = model.id,
        userId = model.userId,
        title = model.title,
        completed = model.completed
    )
}