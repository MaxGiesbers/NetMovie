package com.net.movie.Home.data.repository

import com.net.movie.Home.data.data_source.Resource
import com.net.movie.Home.data.models.PopularMovies
import com.net.movie.Home.data.models.testing

interface MovieRepository
{
    suspend fun getPopularMovies(): Resource<PopularMovies>
}