package com.example.a01_20210778

import android.content.Context
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchBookService(val context: Context) {
    private val service: AladinBookSearch

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(context.resources.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(AladinBookSearch::class.java)
    }

    suspend fun getBooks(query: String): List<BookData>? {
        Log.d("SearchBookService", "Searching books with query: $query")

        try {
            val searchBookResponse = service.getBooksByName(context.resources.getString(R.string.ApiKey), query)

            Log.d("SearchBookService", "Response received: $searchBookResponse")

            // Null이 아닌 경우 빈 리스트로 반환
            return searchBookResponse.books
        } catch (e: Exception) {
            Log.e("SearchBookService", "Error fetching books", e)
            return emptyList()
        }
    }
}