package com.net.movie.data.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import javax.inject.Inject
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
class TmdbHttpClient @Inject constructor()  {

    fun getHttpClient() = HttpClient(Android) {

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.i("KTOR Logging", message)
                }
            }
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        install(ResponseObserver) {
            onResponse {
            }
        }
    }
}