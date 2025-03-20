package com.example.a01_20210778

import android.content.Intent
import android.os.Bundle
import android.view.View

import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a01_20210778.databinding.ActivityBestSellerBinding
import com.example.a01_20210778.ui.BestSellerViewModel
import com.example.a01_20210778.ui.BestSellerViewModelFactory




class BestSellerActivity  : AppCompatActivity() {

    private lateinit var bestSellerRepository: BestSellerRepository

    val adapter = SearchBookAdapter()
    val binding by lazy { ActivityBestSellerBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bestSellerService = BestSellerService(applicationContext)
        bestSellerRepository = BestSellerRepository(bestSellerService)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.listBestSellerRv.layoutManager=layoutManager
        binding.listBestSellerRv.adapter = adapter

        val bestSellerViewModel : BestSellerViewModel by viewModels{
            BestSellerViewModelFactory(bestSellerRepository)
        }


        bestSellerViewModel.books.observe(this) { books ->
            adapter.books = books
            adapter.notifyDataSetChanged()
        }
        bestSellerViewModel.getBooks()
        adapter.setOnItemClickListener(object: SearchBookAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val searchBook = adapter.books?.get(position)

                val intent = Intent(view.context, BookDetailActivity::class.java).apply {
                    putExtra("searchBook", searchBook) // SearchBook 객체를 전달
                }

                view.context.startActivity(intent)

            }
        })



    }



}




