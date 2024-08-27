package com.merp.jet.my.pdf.reader.app.screens.splash

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.merp.jet.my.pdf.reader.app.R

@Composable
fun SplashScreen(navController: NavController) {
    Column(
        Modifier.fillMaxSize(),
        Arrangement.Center, Alignment.CenterHorizontally,
    ) {
        Surface(
            Modifier
                .padding(15.dp)
                .size(300.dp),
            border = BorderStroke(2.dp, Color.LightGray),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.lbl_pdf_reader),
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Red.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = stringResource(R.string.lbl_change_yourself),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Gray
                )
            }
        }
    }
}