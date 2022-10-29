package com.wcp.data.extensions

import android.accounts.NetworkErrorException
import com.squareup.moshi.JsonDataException
import com.wcp.data.helper.Constants.ERROR_JSON_CONVERSION
import com.wcp.data.helper.Constants.ERROR_MESSAGE_GENERAL
import com.wcp.data.helper.Constants.ERROR_TITLE_GENERAL
import com.wcp.domain.exception.DataException
import com.wcp.domain.type.Either
import java.net.UnknownHostException

fun Exception.convertEither(): Either<DataException, Nothing> =
    when (this) {
        is NetworkErrorException, is UnknownHostException -> Either.Left(DataException.Network)
        is JsonDataException -> Either.Left(
            DataException.Api(
                message = ERROR_JSON_CONVERSION,
                title = ERROR_TITLE_GENERAL,
                errorCode = -1
            )
        )
        else -> Either.Left(
            DataException.Api(
                message = ERROR_MESSAGE_GENERAL,
                title = ERROR_TITLE_GENERAL,
                errorCode = -1
            )
        )
    }