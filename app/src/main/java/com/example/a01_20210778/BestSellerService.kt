package com.example.a01_20210778

import android.content.Context
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BestSellerService (val context: Context) {
    private val service: AladinBestSeller

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(context.resources.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(AladinBestSeller::class.java)
    }

    suspend fun getBooks(): List<BookData>? {

        try {
            val bestSellerServiceResponse = service.getBestSeller(context.resources.getString(R.string.ApiKey))

            Log.d("BestSellerService", "Response received: $bestSellerServiceResponse")

            // Null이 아닌 경우 빈 리스트로 반환
            return bestSellerServiceResponse.books
        } catch (e: Exception) {
            Log.e("BestSellerService", "Error fetching books", e)
            return emptyList()
        }
    }
    suspend fun getBookAtIndex(index: Int): BookData? {
        try {
            val bestSellerServiceResponse = service.getBestSeller(context.resources.getString(R.string.ApiKey))

            Log.d("BestSellerService", "Response received: $bestSellerServiceResponse")

            // 인덱스에 해당하는 책 데이터 반환, 인덱스가 유효하지 않으면 null 반환
            return bestSellerServiceResponse.books?.getOrNull(index)  // 인덱스가 존재하지 않으면 null 반환
        } catch (e: Exception) {
            Log.e("BestSellerService", "Error fetching books", e)
            return null  // 오류 발생 시 null 반환
        }
    }
}