package com.yeen.data.webservice

import com.yeen.data.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

const val API_KEY = "40921f35e7d4ca1a0b1bef750047a2a3"
const val BASE_URL = "https://api.themoviedb.org/3/"

interface MovieService {
    @GET("discover/movie")
    fun getRank(@QueryMap par: Map<String, String>) : Call<MovieResponse>
}