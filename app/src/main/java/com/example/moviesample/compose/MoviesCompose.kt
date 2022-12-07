package com.example.moviesample.compose

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviesample.ImagesBaseUrl
import com.example.moviesample.MovieUrl
import com.example.moviesample.PicSizeW500
import com.example.moviesample.api.model.UpcomingMovie
import com.example.moviesample.viewmodel.ExperimentalViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesCompose(viewModel: ExperimentalViewModel) {
    val upcomingMovies by viewModel.upcomingMovies.collectAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Latest Popular Movies") }, modifier = Modifier.statusBarsPadding())
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                top = 8.dp,
                start = 8.dp,
                end = 8.dp,
                bottom = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding() + 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(it)
        ) {
            items(upcomingMovies) { movie ->
                ItemComposable(upcomingMovie = movie)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemComposable(upcomingMovie: UpcomingMovie) {
    val context = LocalContext.current
    Card(modifier = Modifier.aspectRatio(1.77f), onClick = {

        onClick(context, upcomingMovie)
    }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = ImagesBaseUrl + PicSizeW500 + upcomingMovie.backdrop_path,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
            )
            Surface(
                modifier = Modifier.align(Alignment.TopStart),
                color = Color.Black.copy(alpha = 0.5f),
            ) {
                Text(
                    color = Color.White,
                    text = upcomingMovie.title ?: "",
                    modifier = Modifier.padding(8.dp)
                )
            }
            Surface(
                modifier = Modifier.align(Alignment.BottomEnd),
                color = Color.Black.copy(alpha = 0.5f),
            ) {
                Text(
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall,
                    text = upcomingMovie.id.toString(),
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

fun onClick(contex: Context, movie: UpcomingMovie) {
    val url = MovieUrl + movie.id.toString()
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
    }
    contex.startActivity(intent)
}