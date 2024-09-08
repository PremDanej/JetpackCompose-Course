package com.merp.jet.my.pdf.reader.app.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.merp.jet.my.pdf.reader.app.R
import com.merp.jet.my.pdf.reader.app.components.FABContent
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

    val currentUserName =
        if (!FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty())
            FirebaseAuth.getInstance().currentUser?.email?.split("@")?.get(0)
        else "Anonymous"

    Column(
        Modifier
            .fillMaxSize(),
        Arrangement.Top
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
        Row(Modifier.horizontalScroll(rememberScrollState())) {
            ListCard()
            ListCard()
            ListCard()
        }
    }
}

@Preview
@Composable
fun ListCard(
    book: MBook = MBook(
        "abc",
        "Running",
        "Me and You",
        "Hello World"
    ),
    onPressedDetail: (String) -> Unit = {}
) {

    // val context: Context = LocalContext.current
    // val resources: Resources = context.resources
    // val displayMetrics: DisplayMetrics = resources.displayMetrics
    // val screenWidth: Int = displayMetrics.widthPixels
    // val spacing: Dp = 10.dp

    Card(
        onClick = { onPressedDetail.invoke(book.title.toString()) },
        elevation = CardDefaults.elevatedCardElevation(),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = CardDefaults.outlinedCardBorder(),
        modifier = Modifier
            .padding(10.dp)
            .height(250.dp)
            .width(180.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth(),
        ) {
            Row {
                Image(
                    painter = painterResource(
                        id = R.drawable.ic_launcher_background
                    )
                    /*rememberAsyncImagePainter(
                    model = "",
                )*/, contentDescription = "Image",
                    modifier = Modifier
                        .height(140.dp)
                        .width(100.dp),
                    contentScale = ContentScale.FillBounds
                )
                Spacer(modifier = Modifier.width(35.dp))
                Column(
                    Modifier.padding(end = 4.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                        )
                    }

                    BookRating(score = 3.5)
                }
            }

            Text(
                text = book.title.toString(),
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            )
            Text(
                text = book.authors.toString(),
                modifier = Modifier
                    .padding(start = 4.dp, bottom = 4.dp)
                    .fillMaxWidth()
            )

        }
        Row(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom
        ) {
            RoundedButton()
        }
    }
}

@Composable
fun BookRating(score: Double = 4.5) {
    Surface(
        Modifier.height(80.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            Modifier.padding(vertical = 4.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.StarBorder,
                    contentDescription = "Rate",
                )
            }
            Text(text = score.toString())
        }
    }
}

@Composable
fun RoundedButton(
    label: String = "Reading",
    radius: Int = 25,
    onPress: () -> Unit = {}
) {
    Surface(
        Modifier
            .clip(
                RoundedCornerShape(
                    topStartPercent = radius,
                    bottomEndPercent = radius
                )
            ),
        color = MaterialTheme.colorScheme.tertiaryContainer
    ) {
        Column(
            modifier = Modifier
                .width(90.dp)
                .heightIn(40.dp)
                .clickable { onPress.invoke() },
            Arrangement.Center,
            Alignment.CenterHorizontally
        ) {
            Text(text = label)
        }
    }
}