package com.example.a01_20210778

import android.content.Context
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StoreService(val context: Context) {
    private val service: AladinStore

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(context.resources.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(AladinStore::class.java)
    }

    suspend fun getStores(isbn: String): List<StoreData>? {

        try {
            val storeRoot = service.getStoresByISBN(context.resources.getString(R.string.ApiKey),isbn)

            Log.d("StoreService", "Response received: $storeRoot")

            return storeRoot.stores
        } catch (e: Exception) {
            Log.e("StoreService", "Error fetching books", e)
            return emptyList()
        }
    }

}