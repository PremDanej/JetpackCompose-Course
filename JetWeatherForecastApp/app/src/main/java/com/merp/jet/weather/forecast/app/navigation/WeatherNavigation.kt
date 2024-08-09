package com.merp.jet.weather.forecast.app.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.merp.jet.weather.forecast.app.screens.about.AboutScreen
import com.merp.jet.weather.forecast.app.screens.favorite.FavoriteScreen
import com.merp.jet.weather.forecast.app.screens.main.MainViewModel
import com.merp.jet.weather.forecast.app.screens.main.MainScreen
import com.merp.jet.weather.forecast.app.screens.search.SearchScreen
import com.merp.jet.weather.forecast.app.screens.setting.SettingsScreen
import com.merp.jet.weather.forecast.app.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name) {
        composable(WeatherScreens.SplashScreen.name) {
            WeatherSplashScreen(navController = navController)
        }

        // mainScreen/city="Moscow"
        val route: String = WeatherScreens.MainScreen.name
        composable(
            route = "$route/{city}",
            arguments = listOf(
                navArgument(
                    name = "city",
                    builder = { type = NavType.StringType }
                )
            ),
            content = { navBack ->
                navBack.arguments?.getString("city").let { city ->
                    val mainViewModel = hiltViewModel<MainViewModel>()
                    MainScreen(navController = navController, mainViewModel, city = city)
                }
            }
        )
        composable(WeatherScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
        composable(WeatherScreens.AboutScreen.name) {
            AboutScreen(navController = navController)
        }
        composable(WeatherScreens.FavoriteScreen.name) {
            FavoriteScreen(navController = navController)
        }
        composable(WeatherScreens.SettingsScreen.name) {
            SettingsScreen(navController = navController)
        }
    }
}