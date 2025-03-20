package com.example.a01_20210778.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class BookRepository (private val bookDao: BookDao) {
    val allBooks : Flow<List<Book>> = bookDao.getAllBooks()

    val top4Books : Flow<List<Book>> = bookDao.getTop4Books()

     suspend fun addBook(book: Book) {
         withContext(Dispatchers.IO) {
             bookDao.insertBook(book)
         }

    }


    suspend fun removeBook(book: Book) {
        bookDao.deleteBook(book)
    }


}