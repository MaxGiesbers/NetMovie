package com.net.movie.Home.data.models

import kotlinx.serialization.Serializable

@Serializable

data class testing(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)