package com.merp.jet.my.pdf.reader.app.data

data class DataOrException<T, Boolean, E : Exception>(
    var data: T? = null,
    var loading: Boolean? = null,
    var e: E? = null
)