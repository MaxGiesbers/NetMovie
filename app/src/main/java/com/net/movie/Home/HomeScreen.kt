package com.net.movie.Home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.net.movie.Home.data.data_source.Resource
import com.net.movie.shared.layout.MovieList
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun HomeScreen(navHostController: NavHostController, movieViewModel: MovieViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val movies = movieViewModel.movies.collectAsState()
        movies.value?.let {
            when (it) {
                is Resource.Failure -> {
                    Toast.makeText(
                        LocalContext.current,
                        it.exception.message!!,
                        Toast.LENGTH_SHORT
                    )
                }

                Resource.Loading -> { //TODO
                }

                is Resource.Success -> {
                    MovieList(
                        it.result.results,
                        listModifier = Modifier.fillMaxWidth(),
                        rowModifier = Modifier.padding(0.dp),
                        imageModifier =
                        Modifier
                            .width(150.dp)
                            .height(200.dp)
                    )
                }
            }
        }
    }
}