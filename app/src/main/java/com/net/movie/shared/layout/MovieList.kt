package com.net.movie.shared.layout


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button

import androidx.compose.ui.Modifier
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.net.movie.Home.data.models.Movies
import com.net.movie.R
import com.net.movie.ui.theme.AppTheme



@Preview
@Composable
fun PreviewMovielist() {

    val test =  listOf(Movies(
        false,
        "pizza",
        listOf(1,2),1,
        "original language",
        "title",
        "overview text",
        6.0,
        "path",
        "10-01-2022",
        "title",
        false,
        6.0,
        1),
        Movies(
            false,
            "pizza",
            listOf(1,2),1,
            "original language",
            "title",
            "overview text",
            6.0,
            "path",
            "10-01-2022",
            "fix het",
            false,
            6.0,
            1)
        )
    AppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MovieList(
                movies = test,
                listModifier = Modifier.fillMaxWidth(),
                rowModifier = Modifier.padding(0.dp),
                imageModifier =
                Modifier
                    .width(150.dp)
                    .height(200.dp)
            )
        }}

}
@Composable
fun MovieList(
    movies: List<Movies>,
    listModifier: Modifier,
    rowModifier: Modifier,
    imageModifier: Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = listModifier,
        contentPadding = PaddingValues(16.dp)
    )
    {

        items(movies) { item ->
            Card() {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = rowModifier,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                    AsyncImage(
                        model =
                        ImageRequest.Builder(LocalContext.current)
                            .data(item.fullPosterPath)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "movie",
                        contentScale = ContentScale.Fit,
                        modifier = imageModifier
                    )

                    Column( horizontalAlignment = Alignment.Start ,
                        modifier = Modifier.padding(0.dp)) {
                        Text(
                            color = MaterialTheme.colorScheme.primary,
                            text = item.title, fontSize = 20.sp
                        )
                        Text(
                            color = MaterialTheme.colorScheme.secondary,
                            text = item.overview,
                            maxLines = 6, overflow = TextOverflow.Ellipsis, fontSize = 14.sp
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        Button(onClick = {

                        }) {
                            Text(text = "More info")
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}
