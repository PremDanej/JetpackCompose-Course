package com.merp.jet.note.app.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.merp.jet.note.app.R
import com.merp.jet.note.app.componenets.NoteInputText

@ExperimentalMaterial3Api
@Composable
fun NoteScreen() {
    val noteName = remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.padding(0.dp)) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFD3EAF5)),
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Notification Icon"
                )
            })

        // Content
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                text = noteName.value,
                label = "Note",
                onTextChange = { noteName.value = it })
        }
    }
}

@ExperimentalMaterial3Api
@Composable
@Preview(showBackground = true)
fun NoteScreenPreview() {
    NoteScreen()
}