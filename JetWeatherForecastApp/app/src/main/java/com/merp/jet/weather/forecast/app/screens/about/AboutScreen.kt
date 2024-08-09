package com.merp.jet.weather.forecast.app.screens.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.merp.jet.weather.forecast.app.R
import com.merp.jet.weather.forecast.app.widgets.WeatherAppBar

@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(
        topBar = {
            WeatherAppBar(navController = navController,
                title = "About",
                icon = Icons.AutoMirrored.Default.ArrowBack,
                iSMainScreen = false,
                onButtonClicked = { navController.popBackStack() })
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.lbl_app_version),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.lbl_weather_api),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}