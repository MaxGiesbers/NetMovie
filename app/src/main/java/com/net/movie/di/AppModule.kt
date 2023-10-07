package com.net.movie.di

import com.net.movie.Home.data.repository.MovieRepository
import com.net.movie.Home.data.repository.MovieRepositoryImpl
import com.net.movie.data.network.TmdbHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient


@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    fun getHttpClient(httpClient: TmdbHttpClient): HttpClient = httpClient.getHttpClient()
    @Provides
    fun getMovieRepository(impl: MovieRepositoryImpl): MovieRepository = impl
}