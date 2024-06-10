package com.yeen.data.webservice.retrofit

import com.yeen.data.webservice.model.ApiResult
import com.yeen.data.webservice.model.NetworkRequestInfo
import java.lang.reflect.Type

interface NetworkRequestFactory {

    suspend fun <T> create(
        url: String,
        requestInfo: NetworkRequestInfo = NetworkRequestInfo.Builder().build(),
        type: Type
    ): ApiResult<T>
}
