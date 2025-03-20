package com.example.a01_20210778

import android.content.Intent
import android.os.Bundle
import android.view.View

import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.util.query
import com.example.a01_20210778.databinding.ActivitySearchBinding
import com.example.a01_20210778.ui.SearchViewModel
import com.example.a01_20210778.ui.SearchViewModelFactory



class SearchActivity : AppCompatActivity() {

    private lateinit var searchRepository: SearchBookRepository

    val adapter = SearchBookAdapter()
    val binding by lazy { ActivitySearchBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val searchService = SearchBookService(applicationContext)
        searchRepository = SearchBookRepository(searchService)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.searchRv.layoutManager = layoutManager
        binding.searchRv.adapter = adapter


        adapter.setOnItemClickListener(object: SearchBookAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val searchBook = adapter.books?.get(position)

                val intent = Intent(view.context, BookDetailActivity::class.java).apply {
                    putExtra("searchBook", searchBook) // SearchBook 객체를 전달
                }

                view.context.startActivity(intent)

            }
        })

        val searchViewModel : SearchViewModel by viewModels {
            SearchViewModelFactory(searchRepository)
        }

        searchViewModel.books.observe(this) { books ->
            adapter.books = books
            adapter.notifyDataSetChanged()
        }


        binding.btnSearch.setOnClickListener{
            val query = binding.etKeyword.text.toString()
            binding.searchTv.visibility=View.VISIBLE
            searchViewModel.getBooks(query)
        }




    }



}




