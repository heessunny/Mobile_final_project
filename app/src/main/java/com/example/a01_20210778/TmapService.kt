package com.example.a01_20210778

import android.content.Context
import android.util.Log
import com.example.a01_20210778.data.Poi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TmapService(val context: Context) {
    private val service: TmapInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(context.resources.getString(R.string.tmap_base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(TmapInterface::class.java)
    }

    // POI 정보 가져오기
    suspend fun getInfo(keyWord: String): Poi? {
        try {
            val TmapResponse = service.searchPlace(
                accept = "application/json", // Accept 헤더
                appKey = context.resources.getString(R.string.TMAPKey), // API 키
                searchKeyword = keyWord
            )

            Log.d("TmapService", "Response received: $TmapResponse")

            // null check 추가
            return TmapResponse?.searchPoiInfo?.pois?.poi?.firstOrNull() ?: run {
                Log.d("TmapService", "No POI found for the given keyword.")
                null
            }
        } catch (e: Exception) {
            Log.e("TmapService", "Error fetching data", e)
            return null
        }
    }

}