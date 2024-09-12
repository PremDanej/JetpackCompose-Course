package com.merp.jet.my.pdf.reader.app.model

data class Book(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)