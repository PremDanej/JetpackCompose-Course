package com.merp.jet.my.pdf.reader.app.screens.search

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.merp.jet.my.pdf.reader.app.components.InputField
import com.merp.jet.my.pdf.reader.app.components.ReaderAppBar
import com.merp.jet.my.pdf.reader.app.model.Item
import com.merp.jet.my.pdf.reader.app.navigation.ReaderScreens

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: BookSearchViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            ReaderAppBar(
                title = "Search Books",
                navController = navController,
                icon = Icons.AutoMirrored.Default.ArrowBack,
                showProfile = false,
                onBackPressedCallback = { navController.popBackStack() }
            )
        },
    ) { padding ->
        Surface(
            Modifier
                .padding(padding)
                .padding(horizontal = 10.dp)
        ) {
            Column {
                SearchForm { searchQuery ->
                    viewModel.searchBooks(searchQuery)
                }


                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                )

                BookList(navController, viewModel)
            }
        }
    }
}

@Composable
fun SearchForm(
    modifier: Modifier = Modifier, hint: String = "Search", onSearch: (String) -> Unit = {}
) {
    Column {
        val searchQueryState = rememberSaveable { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current
        val valid = remember(searchQueryState.value) {
            searchQueryState.value.trim().isNotEmpty()
        }

        InputField(
            modifier = modifier,
            valueState = searchQueryState,
            label = hint,
            enable = true,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyboardController?.hide()
            },
            imeAction = ImeAction.Done
        )
    }
}

@Composable
fun BookList(navController: NavController, viewModel: BookSearchViewModel = hiltViewModel()) {

    val listOfBooks = viewModel.listOfBooks

    if (viewModel.isLoading) {
        LinearProgressIndicator()
    } else {
        LazyColumn {
            items(listOfBooks) {
                BookRow(it, navController)
            }
        }
    }
}

@Composable
fun BookRow(
    book: Item,
    navController: NavController
) {
    Card(
        onClick = {
            navController.navigate("${ReaderScreens.DetailsScreen.name}/${book.id}")
        },
        Modifier.padding(vertical = 5.dp),
        colors = CardDefaults.cardColors(Color.Transparent),
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = CardDefaults.cardElevation(0.dp),
    ) {
        Row(
            Modifier.fillMaxWidth()
        ) {
            val imgLink =
                book.volumeInfo.imageLinks.smallThumbnail.ifEmpty {
                    "https://images.freeimages.com/image/previews/1ac/pink-book-icon-png-5694164.png"
                }
            Image(
                rememberAsyncImagePainter(imgLink),
                contentDescription = "Book Image",
                modifier = Modifier
                    .height(100.dp)
                    .width(80.dp)
            )
            Column(

                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = book.volumeInfo.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Author: ${book.volumeInfo.authors}",
                    style = MaterialTheme.typography.labelLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = "Date: ${book.volumeInfo.publishedDate}",
                    style = MaterialTheme.typography.labelLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = "${book.volumeInfo.categories}",
                    style = MaterialTheme.typography.labelLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}