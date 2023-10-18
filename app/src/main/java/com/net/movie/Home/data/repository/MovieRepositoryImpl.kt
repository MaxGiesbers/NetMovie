package com.net.movie.Home.data.repository

import com.net.movie.Home.data.data_source.HttpRoutes
import com.net.movie.Home.data.data_source.Resource
import com.net.movie.Home.data.models.MovieDetail
import com.net.movie.Home.data.models.MovieInformation
import com.net.movie.Home.data.models.MovieTrailer
import com.net.movie.Home.data.models.PopularMovies
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async

import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.jvm.Throws

class MovieRepositoryImpl @Inject constructor(private val httpClient: HttpClient) :
    MovieRepository {
    override suspend fun getPopularMovies(): Resource<PopularMovies> {
        return try {
            Resource.Success(
                httpClient.get {
                    url(HttpRoutes.POPULAIR_MOVIES_URL)
                }.body()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    @Throws
    suspend fun getMovieDetails(movieId: String): MovieDetail? {
        return httpClient.get {
            url(movieId)
        }.body()
    }

    @Throws
    suspend fun getMovieTrailer(movieId: String): MovieTrailer {
        return httpClient.get {
            url("${movieId}/videos")
        }.body()
    }

    override suspend fun getMovie(
        movieId: String,
        dispatcher: CoroutineDispatcher
    ): Resource<MovieInformation> {
        return try {
            withContext(dispatcher) {
                val movieTrailerResponse = async {
                    getMovieTrailer(movieId)
                }.await()

                val movieDetailsResponse = async {
                    getMovieDetails(movieId = movieId)
                }.await()

                return@withContext Resource.Success(
                    MovieInformation(
                        movieDetailsResponse!!.title,
                        movieDetailsResponse.overview,
                        movieDetailsResponse.genres.map { it.name },
                        movieDetailsResponse.release_date,
                        movieDetailsResponse.popularity,
                        movieTrailerResponse.results.first().key
                    )
                )
            }
        } catch (e: Exception) {
            Resource.Failure(e)
        }
    }
}