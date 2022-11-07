package com.wcp.domain.exception

sealed class DataException : RuntimeException {

    constructor() : super()

    constructor(message: String) : super(message)

    object Network : DataException("Unable to connect. Please check connection.")

    object TasksNotFound : DataException("Tasks not found.")

    object Conversion : DataException()

    data class Api(
        override val message: String,
        val title: String = "",
        val errorCode: Int = -1
    ) : DataException(message)
}
