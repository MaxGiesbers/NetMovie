package com.net.movie.Home.data.repository

import com.net.movie.BuildConfig
import com.net.movie.Home.data.data_source.HttpRoutes
import com.net.movie.Home.data.data_source.Resource
import com.net.movie.Home.data.models.MovieDetail
import com.net.movie.Home.data.models.PopularMovies
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import java.lang.Exception
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val httpClient: HttpClient): MovieRepository {
    override suspend fun getPopularMovies(): Resource<PopularMovies> {
        return try {
            Resource.Success(
                httpClient.get {
                    url(HttpRoutes.POPULAIR_MOVIES_URL)
                    parameter("api_key", BuildConfig.apiKey)
                }.body()
            )
        }catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }


    //TODO get request fails due a unexpected null
    override suspend fun getMovieDetails(movieId: String): Resource<MovieDetail> {
        return try {
            Resource.Success(
                httpClient.get {
                    url(HttpRoutes.BASE_URL + movieId)
                    parameter("api_key", BuildConfig.apiKey)
                }.body()
            )
        }
        catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }
}