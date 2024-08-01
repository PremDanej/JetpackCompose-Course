package com.merp.jet.weather.forecast.app.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.merp.jet.weather.forecast.app.R

@Composable
fun WeatherSplashScreen(navController: NavController) {
    Surface(
        modifier = Modifier
            .padding(15.dp)
            .size(300.dp),
        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(2.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.padding(1.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.sun),
                contentDescription = "Icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(95.dp)
            )
            Text(
                text = "Find the Sun?", color = Color.LightGray,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}