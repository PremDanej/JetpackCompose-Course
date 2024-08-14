package com.merp.jet.weather.forecast.app.screens.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.merp.jet.weather.forecast.app.model.Favorite
import com.merp.jet.weather.forecast.app.navigation.WeatherScreens
import com.merp.jet.weather.forecast.app.widgets.WeatherAppBar

@Composable
fun FavoriteScreen(
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    Scaffold(modifier = Modifier.fillMaxWidth(),
        topBar = {
            WeatherAppBar(
                navController = navController,
                title = "Favorite Cities",
                onButtonClicked = navController::popBackStack,
                iSMainScreen = false,
                icon = Icons.AutoMirrored.Default.ArrowBack
            )
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val list = favoriteViewModel.favList.collectAsState().value
                HorizontalDivider(thickness = 0.5.dp)
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn {
                    items(list) {
                        CityRow(it, navController, favoriteViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun CityRow(
    favorite: Favorite,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel
) {
    Surface(
        onClick = { navController.navigate(WeatherScreens.MainScreen.name + "/${favorite.city}") },
        modifier = Modifier
            .padding(horizontal = 14.dp, vertical = 5.dp)
            .fillMaxWidth()
            .height(50.dp), shape = CircleShape.copy(topEnd = CornerSize(8.dp)),
        color = Color(0xFFB2DFDB)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = favorite.city, modifier = Modifier.padding(4.dp))
            Surface(
                modifier = Modifier.padding(0.dp),
                shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
                color = Color(0xFFD1E3E1),
            ) {
                Text(
                    text = favorite.country,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            IconButton(onClick = { favoriteViewModel.deleteFavorite(favorite) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.Red.copy(alpha = 0.5f)
                )
            }
        }
    }
}