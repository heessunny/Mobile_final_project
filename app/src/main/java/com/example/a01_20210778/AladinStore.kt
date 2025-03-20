package com.example.a01_20210778

import retrofit2.http.GET
import retrofit2.http.Query

interface AladinStore {
        @GET("ItemOffStoreList.aspx")
        suspend fun getStoresByISBN(
            @Query("ttbkey") apiKey: String,
            @Query("ItemId") itemId: String,
            @Query("ItemIdType") itemIdType: String = "ISBN13",
            @Query("output") output: String = "js",
            @Query("Version") version: String = "20131101"
        ): StoreRoot



}