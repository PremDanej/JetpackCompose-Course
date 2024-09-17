package com.merp.jet.my.pdf.reader.app.repository

import com.merp.jet.my.pdf.reader.app.data.Resource
import com.merp.jet.my.pdf.reader.app.model.Item
import com.merp.jet.my.pdf.reader.app.network.BooksApi
import javax.inject.Inject

class BookRepository @Inject constructor(private val api: BooksApi) {

    suspend fun getBooks(searchQuery: String): Resource<List<Item>> {
        return try {
            Resource.Loading(data = true)
            val itemList = api.getAllBooks(searchQuery).items
            if (itemList.isNotEmpty()) {
                Resource.Loading(data = false)
            }
            Resource.Success(itemList)
        } catch (e: Exception) {
            Resource.Error(message = e.message.toString())
        }
    }

    suspend fun getBookInfo(bookId: String): Resource<Item> {
        val response = try {
            Resource.Loading(data = true)
            api.getBookInfo(bookId)

        } catch (e: Exception) {
            return Resource.Error(message = e.message.toString())
        }
        Resource.Loading(false)
        return Resource.Success(data = response)
    }
}