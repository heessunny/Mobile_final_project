package com.example.a01_20210778.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.a01_20210778.data.Book
import com.example.a01_20210778.data.BookRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class StorageViewModel (val repo: BookRepository): ViewModel() {

    var allBooks: LiveData<List<Book>> = repo.allBooks.asLiveData()

    var top4Books: LiveData<List<Book>> = repo.top4Books.asLiveData()

    fun addBook(book: Book)= viewModelScope.launch {
        repo.addBook(book)
    }

    fun remove(book: Book) =viewModelScope.launch(Dispatchers.IO) {
        repo.removeBook(book)
    }


}