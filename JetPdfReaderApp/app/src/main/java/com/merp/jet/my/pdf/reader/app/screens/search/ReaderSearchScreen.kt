package com.merp.jet.my.pdf.reader.app.screens.search

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.merp.jet.my.pdf.reader.app.components.InputField
import com.merp.jet.my.pdf.reader.app.components.ReaderAppBar
import com.merp.jet.my.pdf.reader.app.model.MBook

@Preview
@Composable
fun SearchScreen(navController: NavController = NavHostController(LocalContext.current)) {
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
                SearchForm() {
                    Log.d("TAG", "SearchScreen: String $it")
                }

                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                )

                BookList()
            }
        }
    }
}

@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    loading: Boolean = false,
    hint: String = "Search",
    onSearch: (String) -> Unit = {}
) {
    Column {
        val searchQueryState = rememberSaveable { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current
        val valid = remember(searchQueryState.value) {
            searchQueryState.value.trim().isNotEmpty()
        }

        InputField(
            valueState = searchQueryState, label = hint, enable = true,
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
fun BookList() {
    val listOfBooks = listOf(
        MBook("abc", "Running", "You Suf Pathan", "Hello World"),
        MBook("xyz", "Hello", "M. Khalifa", "Hello World"),
        MBook("pqr", "Hello Again", "Andrew Tatte", "Hello World"),
        MBook("hello", "Hello Again and Again bro", "Me and You, You, Me, She, He", "Hello World"),
    )

    LazyColumn {
        items(listOfBooks) {
            BookRow(it)
        }
    }

}

@Preview
@Composable
fun BookRow(
    book: MBook = MBook(
        "abc",
        "Running",
        "Me and You",
        "Hello World"
    )
) {
    Card(
        onClick = {},
        Modifier.padding(vertical = 5.dp),
        colors = CardDefaults.cardColors(Color.Transparent),
        border = CardDefaults.outlinedCardBorder(),
        elevation = CardDefaults.elevatedCardElevation(),
    ) {
        Row(
            Modifier.fillMaxWidth()
        ) {
            val imgLink =
                "https://images.freeimages.com/image/previews/1ac/pink-book-icon-png-5694164.png"
            Image(
                rememberAsyncImagePainter(imgLink),
                contentDescription = "Book Image",
                modifier = Modifier.height(120.dp)
            )
            Column(
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = book.title.toString(),
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Author: ${book.authors}",
                    style = MaterialTheme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = "Date: 2024-09-23",
                    style = MaterialTheme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontStyle = FontStyle.Italic
                )
                Text(
                    text = "Computer",
                    style = MaterialTheme.typography.bodyLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}