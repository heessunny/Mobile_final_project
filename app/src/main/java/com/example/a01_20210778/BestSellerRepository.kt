package com.example.a01_20210778

class BestSellerRepository (private val bestSellerService: BestSellerService) {

    suspend fun getBooks(): List<BookData>? {
        return bestSellerService.getBooks()
    }
    suspend fun getBookAtIndex(index: Int): BookData?{
        return bestSellerService.getBookAtIndex(index)
    }
}