package com.net.movie.Home.data.models


data class MovieInformation (
    val title: String,
    val description: String,
    val genre: List<String>,
    val releaseDate: String,
    val popularity: Double,
    val trailerId: String
)
