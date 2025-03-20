package com.example.a01_20210778

import android.app.Application
import com.example.a01_20210778.data.BookDatabase
import com.example.a01_20210778.data.BookRepository


class BookApplication: Application() {
    val bookDatabase by lazy {
        BookDatabase.getDatabase(this)
    }

    val bookRepo by lazy {
        BookRepository(bookDatabase.bookDao())
    }
}