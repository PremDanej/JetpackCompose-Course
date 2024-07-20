package com.merp.jet.note.app.screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merp.jet.note.app.data.NoteDataSource
import com.merp.jet.note.app.model.Note
import com.merp.jet.note.app.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    // private var noteList = mutableStateListOf<Note>()
    private  val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect{
                    listOfNotes ->
                    if(listOfNotes.isEmpty()){
                        Log.d("EMPTY" , "Empty list")
                    }else{
                        _noteList.value = listOfNotes
                    }
                }
        }
        // noteList.addAll(NoteDataSource().loadNotes())
    }

    fun addNote(note: Note){
        viewModelScope.launch { repository.addNote(note) }
    }

    suspend fun updateNote(note: Note){
        viewModelScope.launch { repository.updateNote(note) }
    }

    fun removeNote(note: Note){
        viewModelScope.launch { repository.deleteNote(note) }
    }

    suspend fun remoteAllNote(){
        viewModelScope.launch { repository.deleteAllNotes() }
    }
}