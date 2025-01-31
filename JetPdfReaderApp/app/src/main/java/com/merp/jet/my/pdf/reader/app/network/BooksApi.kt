package com.merp.jet.my.pdf.reader.app.network

import com.merp.jet.my.pdf.reader.app.model.Book
import com.merp.jet.my.pdf.reader.app.model.Item
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApi {

    @GET("volumes")
    suspend fun getAllBooks(@Query("q") query: String): Book

    @GET("volumes/{bookId}")
    suspend fun getBookInfo(@Path("bookId") bookId: String): Item
}