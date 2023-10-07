package com.net.movie.Home.data.data_source

import android.util.Log
import com.net.movie.Home.data.models.PopularMovies
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import java.lang.Exception

//interface MovieService {
//
//   suspend fun getPopularMovies(): Resource<PopularMovies>
//
//   companion object {
//       fun create(): MovieService {
//
//           return MovieServiceImpl(client = HttpClient(Android) {
//               install(Logging) {
//                   logger = object : Logger {
//                       override fun log(message: String) {
//                           Log.i("KTOR Logging", message)
//                       }
//                   }
//               }
//               install(ContentNegotiation) {
//                   json(Json {
//                       prettyPrint = true
//                       isLenient = true
//                       ignoreUnknownKeys = true
//                   })
//               }
//
//               install(ResponseObserver) {
//                   onResponse {
//                   }
//               }
//           })
//       }
//   }
//}
//
//
//class MovieServiceImpl (private val client: HttpClient): MovieService {
//    override suspend fun getPopularMovies(): Resource<PopularMovies> {
//        return try {
//            Resource.Success(client.get {
//                url(HttpRoutes.POPULAIR_MOVIES_URL)
//                parameter("api_key", "61e203bbd75e5291683c463ea912933e")
//            }.body())
//        } catch (e : Exception) {
//            Resource.Failure(e)
//        }
//    }
//}