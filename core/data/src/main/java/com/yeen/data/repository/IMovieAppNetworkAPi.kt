package com.yeen.data.repository

import com.yeen.data.response.MovieResponses
import com.yeen.data.webservice.model.ApiResult

interface IMovieAppNetworkApi {
    suspend fun getMovies() : ApiResult<List<MovieResponses>>
}