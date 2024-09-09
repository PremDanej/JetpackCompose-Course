package com.merp.jet.my.pdf.reader.app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.merp.jet.my.pdf.reader.app.R
import com.merp.jet.my.pdf.reader.app.model.MBook
import com.merp.jet.my.pdf.reader.app.navigation.ReaderScreens

@Composable
fun ReaderLogo(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = stringResource(R.string.lbl_pdf_reader),
        style = MaterialTheme.typography.headlineLarge,
        color = Color.Red.copy(alpha = 0.7f)
    )
}

@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    emailState: MutableState<String>,
    label: String,
    enable: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    InputField(
        modifier = modifier,
        valueState = emailState,
        label = label,
        enable = enable,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        onAction = onAction
    )
}

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    label: String,
    enable: Boolean,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
) {

    OutlinedTextField(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(),
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = label) },
        singleLine = isSingleLine,
        enabled = enable,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction)
    )
}

@Composable
fun PasswordInput(
    modifier: Modifier,
    passwordState: MutableState<String>,
    label: String,
    enable: Boolean,
    passwordVisibility: MutableState<Boolean>,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    val visualTransformation =
        if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation()
    OutlinedTextField(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(),
        value = passwordState.value,
        onValueChange = { passwordState.value = it },
        label = { Text(text = label) },
        singleLine = true,
        enabled = enable,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        visualTransformation = visualTransformation,
        trailingIcon = {
            PasswordVisibility(passwordVisibility)
        }
    )
}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
    val visible = passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value = !visible }) {
        Icon(
            imageVector = if (visible) Icons.Default.Close else Icons.Default.Done,
            contentDescription = "Password"
        )
    }
}

@Composable
fun FABContent(onTap: (String) -> Unit) {
    FloatingActionButton(
        onClick = { onTap("") },
        shape = RoundedCornerShape(50.dp),
        containerColor = MaterialTheme.colorScheme.tertiaryContainer
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add a Book",
        )
    }
}

@Composable
fun TitleSection(
    modifier: Modifier = Modifier,
    label: String = stringResource(R.string.app_name)
) {
    Surface(modifier.padding(5.dp)) {
        Column {
            Text(text = label, style = MaterialTheme.typography.bodyLarge, fontSize = 20.sp)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReaderAppBar(
    title: String,
    showProfile: Boolean = true,
    navController: NavController
) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (showProfile) {
                    Icon(imageVector = Icons.Default.Face, contentDescription = "Profile")
                    Spacer(modifier = Modifier.width(10.dp))
                }
                Text(
                    text = title,
                    color = Color.Red.copy(0.8f),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        actions = {
            IconButton(onClick = {
                FirebaseAuth.getInstance().signOut().run {
                    navController.navigate(ReaderScreens.LoginScreen.name)
                }
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.Logout,
                    contentDescription = "Sign out",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    )
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
                val imgLink =
                    "https://images.freeimages.com/image/previews/1ac/pink-book-icon-png-5694164.png"
                Image(
                    painter = /*painterResource(
                        id = R.drawable.ic_launcher_background
                    )*/
                    rememberAsyncImagePainter(
                        model = imgLink,
                    ),
                    contentDescription = "Image",
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
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            )
            Text(
                text = book.authors.toString(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
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
            RoundedButton(radius = 30)
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