package com.merp.jet.my.pdf.reader.app.screens.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merp.jet.my.pdf.reader.app.data.Resource
import com.merp.jet.my.pdf.reader.app.model.Item
import com.merp.jet.my.pdf.reader.app.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookSearchViewModel @Inject constructor(private val repository: BookRepository) :
    ViewModel() {

    var listOfBooks: List<Item> by mutableStateOf(listOf())
    var isLoading: Boolean by mutableStateOf(true)

    init {
        loadBooks()
    }

    private fun loadBooks() {
        searchBooks("android")
    }

    internal fun searchBooks(query: String) {
        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) return@launch
            try {
                when (val response = repository.getBooks(query)) {
                    is Resource.Success -> {
                        listOfBooks = response.data!!
                        if (listOfBooks.isNotEmpty()) isLoading = false
                    }

                    is Resource.Error -> {
                        Log.e("TAG", "searchBooks: Failed getting books")
                    }

                    else -> isLoading = false
                }
            } catch (e: Exception) {
                Log.d("TAG", "searchBooks: ${e.message}")
            }
        }
    }
}