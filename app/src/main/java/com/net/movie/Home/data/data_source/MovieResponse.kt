package com.net.movie.Home.data.data_source

import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse (
    val body: String,
    val title: String,
    val id: String
)


@Serializable
data class response(
    val page: String,
    val results: List<MovieInformation>

)

@Serializable
data class MovieInformation(
    val adult: Boolean,
    val back_drop_path: String,
    val genre_ids: List<Int>,
    val id: String,
    val original_language: String,
    val original_title: String,
    val overview: String
)


