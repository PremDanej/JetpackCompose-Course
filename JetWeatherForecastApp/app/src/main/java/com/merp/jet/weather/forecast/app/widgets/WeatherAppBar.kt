package com.merp.jet.weather.forecast.app.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.merp.jet.weather.forecast.app.R
import com.merp.jet.weather.forecast.app.navigation.WeatherScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    title: String = stringResource(id = R.string.app_name),
    icon: ImageVector? = null,
    iSMainScreen: Boolean = true,
    elevation: Dp = 0.dp,
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {

    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        ShowSettingDropDownMenu(showDialog = showDialog, navController = navController)
    }

    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.Black,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp)
            )
        },
        actions = {
            if (iSMainScreen) {
                IconButton(onClick = {
                    onAddActionClicked.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.Black
                    )
                }
                IconButton(onClick = { showDialog.value = true }) {
                    Icon(
                        imageVector = Icons.Rounded.MoreVert,
                        contentDescription = "More",
                        tint = Color.Black
                    )
                }
            } else Box {}
        },
        navigationIcon = {
            if (icon != null) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Back",
                        tint = Color.Black,
                        modifier = Modifier.clickable { onButtonClicked.invoke() }
                    )
                }
            }
        }
    )
}

@Composable
fun ShowSettingDropDownMenu(
    showDialog: MutableState<Boolean>, navController: NavController
) {
    val expanded by remember {
        mutableStateOf(showDialog)
    }
    val items = listOf("About", "Favorites", "Setting")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 80.dp, right = 20.dp)
    ) {
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = {
                expanded.value = false
            },
            modifier = Modifier
                .width(120.dp)
                .background(Color.White)
        ) {
            items.forEachIndexed { _, element ->
                DropdownMenuItem(text = { Text(text = element) },
                    onClick = {
                        expanded.value = false
                        showDialog.value = false
                        navController.navigate(
                            route = when (element) {
                                "About" -> WeatherScreens.AboutScreen.name
                                "Favorites" -> WeatherScreens.FavoriteScreen.name
                                else -> WeatherScreens.SettingsScreen.name
                            }
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = when (element) {
                                "About" -> Icons.Default.Info
                                "Favorites" -> Icons.Default.Favorite
                                else -> Icons.Default.Settings
                            }, contentDescription = "Leading Icon",
                            tint = Color.Black
                        )
                    }
                )
            }
        }
    }
}
