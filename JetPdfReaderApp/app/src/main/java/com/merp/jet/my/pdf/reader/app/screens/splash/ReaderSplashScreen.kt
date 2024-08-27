package com.merp.jet.my.pdf.reader.app.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.merp.jet.my.pdf.reader.app.R
import com.merp.jet.my.pdf.reader.app.navigation.ReaderScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            0.9f,
            tween(
                800,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                },
            )
        )
        delay(2000L)
    }

    Column(
        Modifier.fillMaxSize(),
        Arrangement.Center, Alignment.CenterHorizontally,
    ) {
        Surface(
            Modifier
                .padding(15.dp)
                .size(300.dp)
                .scale(scale.value),
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