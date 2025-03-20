package com.example.a01_20210778.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a01_20210778.data.BookRepository

class StorageViewModelFactory (private val bookRepository: BookRepository): ViewModelProvider.Factory   {
    // ViewModel 객체를 생성하는 함수를 재정의
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // 생성하려는 클래스가 FoodViewModel 일 경우 객체 생성
        if (modelClass.isAssignableFrom(StorageViewModel::class.java)) {
            return StorageViewModel(bookRepository) as T //내가 만든 뷰 모델을 반환하게
        }
        return IllegalArgumentException("Unknown ViewModel class") as T
    }
}