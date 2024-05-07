package com.yeen.main.home

import android.database.Observable
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeen.data.repository.MovieRepo
import com.yeen.data.request.MovieRequest
import com.yeen.data.response.MovieItem
import com.yeen.data.response.MovieResponse
import com.yeen.data.webservice.API_KEY
import com.yeen.data.webservice.RestApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : ViewModel() {

    val moviesFlow = MutableStateFlow<List<MovieItem>>(listOf())

    init {
        viewModelScope.launch {
            kotlin.runCatching {
                MovieRepo.fetchMovies()
            }.onSuccess {fetchedMovies ->
                Log.d("test", "movieVM() onSuccess")
                moviesFlow.value = fetchedMovies.results
            }.onFailure {
                Log.d("test", "movieVM() onFailure")
            }
        }
    }


    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object Home : UiEvent()
    }
}
