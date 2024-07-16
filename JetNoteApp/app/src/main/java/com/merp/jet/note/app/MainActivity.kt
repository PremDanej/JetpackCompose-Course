package com.merp.jet.note.app

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.merp.jet.note.app.model.Note
import com.merp.jet.note.app.screen.NoteScreen
import com.merp.jet.note.app.ui.theme.JetNoteAppTheme

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            JetNoteAppTheme {
                Column {
                    MyApp {
                        val notes = remember {
                            mutableStateListOf<Note>()
                        }
                        NoteScreen(notes = notes,
                            onAddNote = {
                                notes.add(it)
                            },
                            onRemoveNote = {
                                notes.remove(it)
                            })
                    }
                }
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    content()
}