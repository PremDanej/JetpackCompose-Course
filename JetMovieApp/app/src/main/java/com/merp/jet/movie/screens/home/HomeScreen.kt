package com.merp.jet.movie.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.merp.jet.movie.model.Movie
import com.merp.jet.movie.model.getMovies
import com.merp.jet.movie.navigation.MovieScreens
import com.merp.jet.movie.widgets.MovieRow

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Movie") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                MainContent(navController)
            }
        }
    )
}

@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovies()
) {
    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
        LazyColumn {
            items(items = movieList) {
                MovieRow(movie = it) { movie ->
                    navController.navigate(route = MovieScreens.DetailsScreen.name + "/$movie")
                }
            }
        }
    }
}