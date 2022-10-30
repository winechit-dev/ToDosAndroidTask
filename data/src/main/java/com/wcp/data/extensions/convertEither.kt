package com.wcp.data.extensions

import android.accounts.NetworkErrorException
import com.squareup.moshi.JsonDataException
import com.wcp.data.helper.Constants.ERROR_JSON_CONVERSION
import com.wcp.data.helper.Constants.ERROR_MESSAGE_GENERAL
import com.wcp.data.helper.Constants.ERROR_TITLE_GENERAL
import com.wcp.domain.exception.DataException
import java.net.UnknownHostException

fun Exception.convertException(): DataException =
    when (this) {
        is NetworkErrorException, is UnknownHostException -> DataException.Network
        is JsonDataException ->
            DataException.Api(
                message = ERROR_JSON_CONVERSION,
                title = ERROR_TITLE_GENERAL,
                errorCode = -1
            )

        else ->
            DataException.Api(
                message = ERROR_MESSAGE_GENERAL,
                title = ERROR_TITLE_GENERAL,
                errorCode = -1
            )
    }