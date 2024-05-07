package com.yeen.data.webservice

import com.yeen.data.response.MovieResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RestApi {

    private val theMovieService: MovieService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        theMovieService = retrofit.create(MovieService::class.java)
    }

    fun getMovieListRetrofit(param: Map<String, String>): Call<MovieResponse> {
        return theMovieService.getRank(param)
    }
}