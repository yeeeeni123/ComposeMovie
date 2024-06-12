package com.yeen.data.repository

import com.google.gson.reflect.TypeToken
import com.yeen.data.response.MovieResponses
import com.yeen.data.webservice.model.ApiResult
import com.yeen.data.webservice.retrofit.NetworkRequestFactory
import javax.inject.Inject

class MovieAppNetworkApi @Inject constructor(
    private val networkRequestFactory: NetworkRequestFactory
) : IMovieAppNetworkApi{
    override suspend fun getMovies(): ApiResult<List<MovieResponses>> {
        return networkRequestFactory.create(
            url = "top250.json",
            type = object : TypeToken<List<MovieResponses>>(){}.type
        )
    }
}