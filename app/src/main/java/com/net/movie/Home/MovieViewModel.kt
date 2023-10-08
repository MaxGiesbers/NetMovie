package com.net.movie.Home

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.net.movie.Home.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel

class MovieViewModel @Inject constructor(
    val movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {


    init {

        viewModelScope.launch {
            val test = movieRepository.getMovieDetails(checkNotNull(savedStateHandle["movieId"]))
            Log.d("tag", test.toString())
        }
    }

    val movieId: String = checkNotNull(savedStateHandle["movieId"])

    // Fetch the relevant user information from the data layer,
    // ie. userInfoRepository, based on the passed userId argument
   // private val userInfo: Flow<UserInfo> = userInfoRepository.getUserInfo(userId)

// …

}