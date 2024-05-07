package com.yeen.data.repository

import com.yeen.data.request.MovieRequest
import com.yeen.data.response.MovieResponse
import com.yeen.data.webservice.KtorClient
import io.ktor.client.request.get

object MovieRepo {

    suspend fun fetchMovies() : MovieResponse {
//        val url = "https://api.themoviedb.org/3/discover/movie?sort_by=${model.sort_by}&api_key=40921f35e7d4ca1a0b1bef750047a2a3&language=${model.language}&page=${model.page}"
        val url = "https://api.themoviedb.org/3/discover/movie?sort_by=popluarity.desc&api_key=40921f35e7d4ca1a0b1bef750047a2a3&language=ko&page=1"
        return KtorClient.httpClient.get(url)
    }

}