package com.net.movie.Home.data.repository

import com.net.movie.Home.data.data_source.Resource
import com.net.movie.Home.data.models.MovieDetail
import com.net.movie.Home.data.models.MovieInformation
import com.net.movie.Home.data.models.MovieTrailer
import com.net.movie.Home.data.models.Movies
import com.net.movie.Home.data.models.PopularMovies
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface MovieRepository
{
    suspend fun getPopularMovies(): Resource<PopularMovies>
    suspend fun getMovie(movieId: String, dispatcher: CoroutineDispatcher = Dispatchers.IO): Resource<MovieInformation>
}