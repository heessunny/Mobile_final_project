package com.example.a01_20210778.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a01_20210778.SearchBookRepository

class SearchViewModelFactory(private val repo: SearchBookRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // 생성하려는 클래스가 NVViewModel 일 경우 객체 생성
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(repo) as T
        }
        return IllegalArgumentException("Unknown ViewModel class") as T
    }
}