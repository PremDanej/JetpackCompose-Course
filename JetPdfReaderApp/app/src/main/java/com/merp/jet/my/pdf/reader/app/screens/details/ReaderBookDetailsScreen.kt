package com.merp.jet.my.pdf.reader.app.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.merp.jet.my.pdf.reader.app.components.ReaderAppBar

@Preview
@Composable
fun BookDetailsScreen(
    navController: NavController = NavHostController(LocalContext.current),
    bookId: String = ""
) {
    Scaffold(
        topBar = {
            ReaderAppBar(
                title = "Book Details",
                showProfile = false,
                navController = navController,
                icon = Icons.AutoMirrored.Default.ArrowBack,
                onBackPressedCallback = {
                    navController.popBackStack()
                })
        }
    ) {
        Surface(Modifier.padding(it)) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Book Id -> $bookId")
            }
        }
    }
}