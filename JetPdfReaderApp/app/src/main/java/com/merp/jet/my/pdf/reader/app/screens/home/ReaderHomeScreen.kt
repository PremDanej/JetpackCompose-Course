package com.merp.jet.my.pdf.reader.app.screens.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.merp.jet.my.pdf.reader.app.R
import com.merp.jet.my.pdf.reader.app.components.FABContent
import com.merp.jet.my.pdf.reader.app.components.ListCard
import com.merp.jet.my.pdf.reader.app.components.ReaderAppBar
import com.merp.jet.my.pdf.reader.app.components.TitleSection
import com.merp.jet.my.pdf.reader.app.model.MBook
import com.merp.jet.my.pdf.reader.app.navigation.ReaderScreens
import java.util.Locale

@Composable
fun HomeScreen(navController: NavController = NavHostController(LocalContext.current)) {
    Scaffold(
        topBar = {
            ReaderAppBar(
                title = stringResource(id = R.string.app_name),
                navController = navController,
            )
        },
        floatingActionButton = {
            FABContent {}
        }
    ) { padding ->
        Surface(Modifier.padding(padding)) {
            HomeContent(navController)
        }
    }
}

@Composable
fun HomeContent(navController: NavController) {

    val listOfBooks = listOf(
        MBook("abc", "Running", "You Suf Pathan", "Hello World"),
        MBook("xyz", "Hello", "M. Khalifa", "Hello World"),
        MBook("pqr", "Hello Again", "Andrew Tatte", "Hello World"),
        MBook("hello", "Hello Again and Again bro", "Me and You, You, Me, She, He", "Hello World"),
    )

    val currentUserName =
        if (!FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty())
            FirebaseAuth.getInstance().currentUser?.email?.split("@")?.get(0)
        else "Anonymous"

    Column(
        Modifier
            .fillMaxSize(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .align(Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TitleSection(label = stringResource(R.string.lbl_reading_activity))
            Spacer(modifier = Modifier.fillMaxWidth(0.65f))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Avatar",
                    tint = MaterialTheme.colorScheme.tertiaryContainer,
                    modifier = Modifier
                        .size(50.dp)
                        .clickable {
                            navController.navigate(ReaderScreens.StatsScreen.name)
                        }
                )
                Text(
                    text = currentUserName.toString()
                        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Red.copy(0.9f)
                )
            }
        }

        ListCard()
        ReadingRightNowAre(books = listOf(), navController = navController)
        TitleSection(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            label = stringResource(R.string.lbl_reading_section)
        )
        BookListArea(listOfBooks = listOfBooks, navController = navController)

    }
}

@Composable
fun BookListArea(listOfBooks: List<MBook>, navController: NavController) {
    HorizontalScrollableComponent(listOfBooks) {
        // TODO: on Card click navigate to Detail...
        Log.d("TAG", "BookListArea: $it")
    }
}

@Composable
fun HorizontalScrollableComponent(listOfBooks: List<MBook>, onCardPressed: (String) -> Unit) {
    val scrollState = rememberScrollState()
    Row(
        Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState)
    ) {
        for (book in listOfBooks) {
            ListCard(book) {
                onCardPressed(it)
            }
        }
    }
}

@Composable
fun ReadingRightNowAre(books: List<MBook>, navController: NavController) {

}