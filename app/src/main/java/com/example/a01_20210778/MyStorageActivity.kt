package com.example.a01_20210778

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a01_20210778.data.Book
import com.example.a01_20210778.databinding.ActivityMyStorageBinding
import com.example.a01_20210778.ui.StorageViewModel
import com.example.a01_20210778.ui.StorageViewModelFactory


class MyStorageActivity : AppCompatActivity() {

    val TAG = "StorageActivity"

    // view binding object
    val binding by lazy {
        ActivityMyStorageBinding.inflate(layoutInflater)
    }
    val bookViewModel: StorageViewModel by viewModels {

        StorageViewModelFactory(  (application as BookApplication).bookRepo )
    }  //내가 만든 뷰모델 + 뷰모델이 가지고 있는 원래 기능들 해서 완전해짐


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val adapter = StorageBookAdapter(ArrayList<Book>())

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.storageBooksRvList.layoutManager = GridLayoutManager(this, 3)
        binding.storageBooksRvList.adapter = adapter


        bookViewModel.allBooks.observe(this, { books ->
            adapter.books = books
            adapter.notifyDataSetChanged()
        })


    }
}