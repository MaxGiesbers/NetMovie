package com.net.movie.Home.data.repository

import com.net.movie.Home.data.data_source.Resource
import com.net.movie.Home.data.models.MovieDetail
import com.net.movie.Home.data.models.MovieTrailer
import com.net.movie.Home.data.models.Movies
import com.net.movie.Home.data.models.PopularMovies

interface MovieRepository
{
    suspend fun getPopularMovies(): Resource<PopularMovies>

    suspend fun getMovieDetails(movieId: String): Resource<MovieDetail>
    suspend fun getMovieTrailer(movieId: String) : Resource<MovieTrailer>
}