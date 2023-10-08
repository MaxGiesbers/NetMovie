package com.net.movie.Home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.net.movie.Home.data.data_source.Resource
import com.net.movie.shared.layout.MovieList
import androidx.hilt.navigation.compose.hiltViewModel
import com.net.movie.R

@Composable
fun HomeScreen(navHostController: NavHostController, movieViewModel: HomeViewModel = hiltViewModel()) {
    val movies = movieViewModel.movies.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        movies.value?.let {
            when (it) {
                is Resource.Failure -> {
                    Toast.makeText(
                        LocalContext.current,
                        it.exception.message!!,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                Resource.Loading -> {
                    CircularProgressIndicator()
                }

                is Resource.Success -> {

                    Spacer(modifier = Modifier.height(20.dp)) // Add 16dp spacing above the image
                    Image(
                        painterResource(R.drawable.tmdblogo),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    MovieList(
                        it.result.results,
                        listModifier = Modifier.fillMaxWidth(),
                        rowModifier = Modifier.padding(0.dp),
                        imageModifier =
                        Modifier
                            .width(150.dp)
                            .height(200.dp),
                        itemSelected = { identifier ->
                            navHostController.navigate( "movie/$identifier")
                        }
                    )
                }
            }
        }
    }
}