package com.net.movie.Home

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.net.movie.Home.data.data_source.Resource
import com.net.movie.Home.data.models.PopularMovies
import com.net.movie.Home.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel

class MovieViewModel @Inject constructor(
    val movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _trailerId = MutableStateFlow<String?>(null)
    val trailerId = _trailerId.asStateFlow()

    init {

        viewModelScope.launch {
            val test = movieRepository.getMovieDetails(checkNotNull(savedStateHandle["movieId"]))

            when(val trailers  = movieRepository.getMovieTrailer(checkNotNull(savedStateHandle["movieId"]))) {
                is Resource.Failure -> {
                    //TODO
                }
                Resource.Loading -> {
                    //TODO
                }
                is Resource.Success -> {
                    _trailerId.value = trailers.result.results.first().key.toString()
                }
            }
        }
    }

    val movieId: String = checkNotNull(savedStateHandle["movieId"])
}