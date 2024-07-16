package com.merp.jet.note.app.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.merp.jet.note.app.model.Note

@RequiresApi(Build.VERSION_CODES.O)
class NoteDataSource {
    fun loadNotes(): List<Note> {
        return listOf(
            Note(title = "A good day", description = "We going to vacation"),
            Note(title = "A movie day", description = "We are watching a movie"),
            Note(title = "A good day", description = "We going to vacation"),
            Note(title = "A movie day", description = "We are watching a movie"),
            Note(title = "A good day", description = "We going to vacation"),
            Note(title = "A movie day", description = "We are watching a movie"),
            Note(title = "A good day", description = "We going to vacation"),
            Note(title = "A movie day", description = "We are watching a movie"),
            Note(title = "A good day", description = "We going to vacation"),
            Note(title = "A movie day", description = "We are watching a movie"),
            Note(title = "A good day", description = "We going to vacation"),
            Note(title = "A movie day", description = "We are watching a movie"),
            Note(title = "A good day", description = "We going to vacation"),
            Note(title = "A movie day", description = "We are watching a movie"),
        )
    }
}