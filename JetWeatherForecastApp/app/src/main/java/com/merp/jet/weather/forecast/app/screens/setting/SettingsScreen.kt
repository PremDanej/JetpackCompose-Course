package com.merp.jet.weather.forecast.app.screens.setting

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.merp.jet.weather.forecast.app.R
import com.merp.jet.weather.forecast.app.model.Unit
import com.merp.jet.weather.forecast.app.widgets.WeatherAppBar

@Composable
fun SettingsScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {

    var unitToggleState: Boolean by remember { mutableStateOf(false) }
    val measurementUnits: List<String> = listOf("Imperial (F)", "Metric (C)")
    val choiceFromDb: List<Unit> = settingsViewModel.unitList.collectAsState().value
    val defaultChoice: String =
        if (choiceFromDb.isEmpty()) measurementUnits[0] else choiceFromDb[0].unit
    var choiceState: String by remember {
        mutableStateOf(defaultChoice)
    }

    Scaffold(topBar = {
        WeatherAppBar(
            title = "Settings",
            navController = navController,
            icon = Icons.AutoMirrored.Default.ArrowBack,
            onButtonClicked = { navController.popBackStack() },
            iSMainScreen = false
        )
    }) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(15.dp)
            ) {
                Text(
                    text = stringResource(R.string.lbl_change_measurement),
                    modifier = Modifier.padding(bottom = 15.dp)
                )

                IconToggleButton(
                    checked = !unitToggleState, onCheckedChange = {
                        unitToggleState = !it
                        choiceState = (if (unitToggleState) "Imperial (F)" else "Metric (C)")
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .clip(shape = RectangleShape)
                        .padding(5.dp)
                        .background(Color.Magenta.copy(alpha = 0.4f))
                ) {
                    Text(text = if (unitToggleState) "Fahrenheit °F" else " Celsius °C")
                }

                Button(
                    onClick = {
                        settingsViewModel.deleteAllUnits()
                        settingsViewModel.insertUnit(Unit(choiceState))
                    },
                    modifier = Modifier
                        .padding(10.dp),
                    shape = RoundedCornerShape(34.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.onBackground
                    ),
                    border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.onBackground)
                ) {
                    Text(
                        text = "Save",
                        modifier = Modifier.padding(horizontal = 10.dp),
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}