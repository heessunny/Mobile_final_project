package com.example.a01_20210778.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a01_20210778.BookData
import com.example.a01_20210778.SearchBookRepository
import kotlinx.coroutines.launch

class SearchViewModel(val repo: SearchBookRepository) : ViewModel() {

    // 책 목록 표시
    private val _books = MutableLiveData<List<BookData>>()
    val books: LiveData<List<BookData>> = _books

    fun getBooks(query: String) = viewModelScope.launch {
        _books.value = repo.getBooks(query)
    }
}
