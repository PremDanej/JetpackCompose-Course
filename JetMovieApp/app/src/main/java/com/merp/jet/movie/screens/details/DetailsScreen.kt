package com.merp.jet.movie.screens.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.merp.jet.movie.model.Movie
import com.merp.jet.movie.model.getMovies
import com.merp.jet.movie.widgets.MovieRow

@ExperimentalMaterial3Api
@Composable
fun DetailsScreen(navController: NavController, movieId: String?) {
    val newMovieList = getMovies().filter { movie -> movie.id == movieId }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Movie")
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .clickable {
                                navController.popBackStack()
                            }
                    )
                }
            )
        })
    { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Surface(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    MovieRow(newMovieList.first())
                    Spacer(modifier = Modifier.height(8.dp))
                    HorizontalDivider()
                    Text(text = "Movies Images")
                    HorizontalScrollableImageView(newMovieList)
                }
            }
        }
    }
}

@Composable
private fun HorizontalScrollableImageView(newMovieList: List<Movie>) {
    LazyRow {
        items(newMovieList.first().images) { images ->
            Card(
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 10.dp)
                    .size(140.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                border = BorderStroke(width = 0.5.dp, color = Color.LightGray)
            ) {
                Image(
                    painter = rememberImagePainter(data = images),
                    contentDescription = "Movie Poster",
                )
            }
        }
    }
}