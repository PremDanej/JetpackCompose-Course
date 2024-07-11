@file:OptIn(ExperimentalMaterial3Api::class)

package com.merp.jet.movie.screens.home

import android.util.Log
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
import com.merp.jet.movie.MovieRow
import com.merp.jet.movie.navigation.MovieScreens

@Composable
fun HomeScreen(navController: NavController){
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
    movieList: List<String> = listOf(
        "Captain America",
        "Iron Man",
        "Thor",
        "Iron Man 2",
        "Avenger",
        "Iron Man 3",
        "Guardians of the Galaxy 1",
        "Captain America Winter Soldier",
        "Avenger Age of Ultron",
        "Guardians of the Galaxy 2",
        "Avenger Civil War",
        "Avenger Infinity War",
        "Avenger End Game",
        "Thor Love and Thunder",
        "Avenger Secret War"
    )
) {

    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
        LazyColumn() {
            items(items = movieList) {
                MovieRow(movie = it){ movie ->
                    navController.navigate(route = MovieScreens.DetailScreen.name)
                }
            }
        }
    }
}
