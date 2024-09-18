package com.merp.jet.my.pdf.reader.app.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.merp.jet.my.pdf.reader.app.screens.create.CreateAccountScreen
import com.merp.jet.my.pdf.reader.app.screens.details.BookDetailsScreen
import com.merp.jet.my.pdf.reader.app.screens.home.HomeScreen
import com.merp.jet.my.pdf.reader.app.screens.login.LoginScreen
import com.merp.jet.my.pdf.reader.app.screens.search.BookSearchViewModel
import com.merp.jet.my.pdf.reader.app.screens.search.SearchScreen
import com.merp.jet.my.pdf.reader.app.screens.splash.SplashScreen
import com.merp.jet.my.pdf.reader.app.screens.stats.StatsScreen
import com.merp.jet.my.pdf.reader.app.screens.update.BookUpdateScreen

@Composable
fun ReaderNavigation() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ReaderScreens.SplashScreen.name,
        builder = {
            composable(ReaderScreens.SplashScreen.name) {
                SplashScreen(navController = navController)
            }
            composable(ReaderScreens.HomeScreen.name) {
                HomeScreen(navController = navController)
            }
            composable(ReaderScreens.CreateAccountScreen.name) {
                CreateAccountScreen(navController = navController)
            }
            composable(ReaderScreens.LoginScreen.name) {
                LoginScreen(navController = navController)
            }
            composable(ReaderScreens.SearchScreen.name) {
                val searchViewModel = hiltViewModel<BookSearchViewModel>()
                SearchScreen(navController = navController, viewModel = searchViewModel)
            }
            composable(ReaderScreens.UpdateScreen.name) {
                BookUpdateScreen(navController = navController)
            }
            composable(ReaderScreens.DetailsScreen.name) {
                BookDetailsScreen(navController = navController)
            }
            composable(ReaderScreens.StatsScreen.name) {
                StatsScreen(navController = navController)
            }
        }
    )
}