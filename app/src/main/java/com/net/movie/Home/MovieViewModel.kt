package com.net.movie.Home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.net.movie.Home.data.data_source.Resource
import com.net.movie.Home.data.models.PopularMovies
import com.net.movie.Home.data.models.testing
import com.net.movie.Home.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(val movieRepository: MovieRepository): ViewModel() {

    private val _movies = MutableStateFlow<Resource<PopularMovies>?>(null)
    val movies = _movies.asStateFlow()

    init {
        viewModelScope.launch {
            _movies.value = Resource.Loading
            _movies.value = movieRepository.getPopularMovies()
        }
    }
}