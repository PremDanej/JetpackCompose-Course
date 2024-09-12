package com.merp.jet.my.pdf.reader.app.repository

import com.merp.jet.my.pdf.reader.app.data.DataOrException
import com.merp.jet.my.pdf.reader.app.model.Item
import com.merp.jet.my.pdf.reader.app.network.BooksApi
import javax.inject.Inject

class BookRepository @Inject constructor(private val api: BooksApi) {

    private val dataOrException = DataOrException<List<Item>, Boolean, Exception>()
    private val bookInfoDataOrException = DataOrException<Item, Boolean, Exception>()

    suspend fun getBooks(searchQuery: String): DataOrException<List<Item>, Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = api.getAllBooks(searchQuery).items
            if (dataOrException.data!!.isNotEmpty()) {
                dataOrException.loading = false
            }
        } catch (e: Exception) {
            dataOrException.e = e
        }
        return dataOrException
    }

    suspend fun getBookInfo(bookId: String): DataOrException<Item, Boolean, Exception> {
        try {
            bookInfoDataOrException.loading = true
            bookInfoDataOrException.data = api.getBookInfo(bookId)
            if (bookInfoDataOrException.data.toString().isNotEmpty()) {
                dataOrException.loading = false
            }
        } catch (e: Exception) {
            bookInfoDataOrException.e = e
        }
        return bookInfoDataOrException
    }
}