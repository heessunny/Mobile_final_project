package com.example.a01_20210778.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a01_20210778.BestSellerRepository
import com.example.a01_20210778.BookData
import com.example.a01_20210778.SearchBookRepository
import kotlinx.coroutines.launch

class BestSellerViewModel(val repo: BestSellerRepository) : ViewModel() {

    // 책 목록 표시
    private val _books = MutableLiveData<List<BookData>>()
    val books: LiveData<List<BookData>> = _books

    fun getBooks() = viewModelScope.launch {
        _books.value = repo.getBooks()
    }

    fun getBookAtIndex(index: Int) = viewModelScope.launch {
        val book = repo.getBookAtIndex(index)
        if (book != null) {
            // 인덱스에 해당하는 책을 리스트로 감싸서 _books에 저장
            _books.value = listOf(book)
        } else {
            // 유효한 책이 없으면 빈 리스트 반환
            _books.value = emptyList()
        }
    }
}
