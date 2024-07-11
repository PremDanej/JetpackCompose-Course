package com.merp.jet.movie.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.merp.jet.movie.screens.details.DetailsScreen
import com.merp.jet.movie.screens.home.HomeScreen

@Composable
fun MovieNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = MovieScreens.HomeScreen.name){
        composable(MovieScreens.HomeScreen.name){
            // Here we pass where we have to go
             HomeScreen(navController = navController)
        }
        composable(MovieScreens.DetailScreen.name){
            DetailsScreen(navController = navController)
        }
    }
}