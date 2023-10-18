package com.net.movie.Home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.net.movie.Home.data.data_source.Resource
import com.net.movie.Home.data.models.MovieInformation

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

    private val _trailerId = MutableStateFlow<MovieInformation?>(null)
    val trailerId = _trailerId.asStateFlow()

    init {
        viewModelScope.launch {
            when(val response = movieRepository.getMovie(checkNotNull(savedStateHandle["movieId"])))
            {
                is Resource.Failure -> {

                }
                Resource.Loading -> {
                    
                }
                is Resource.Success -> {
                    _trailerId.value = response.result
                }
            }
        }
    }
    val movieId: String = checkNotNull(savedStateHandle["movieId"])
}