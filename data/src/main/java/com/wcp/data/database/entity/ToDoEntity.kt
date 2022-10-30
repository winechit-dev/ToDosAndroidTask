package com.wcp.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "toDos")
data class ToDoEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "userId") val userId: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "completed") val completed: Boolean
)
