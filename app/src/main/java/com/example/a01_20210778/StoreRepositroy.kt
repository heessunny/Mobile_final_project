package com.example.a01_20210778

class StoreRepositroy (private val storeService: StoreService) {

    suspend fun getStores(isbn: String): List<StoreData>? {
        return storeService.getStores(isbn)
    }
}