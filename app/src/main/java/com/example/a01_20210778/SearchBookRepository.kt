package com.example.a01_20210778

class SearchBookRepository (private val searchService: SearchBookService) {

    suspend fun getBooks(query: String): List<BookData>? {
        return searchService.getBooks(query)
    }
}