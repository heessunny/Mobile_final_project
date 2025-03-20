package com.example.a01_20210778

import com.example.a01_20210778.data.TmapResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TmapInterface {
    @GET("pois")
    suspend fun searchPlace(
        @Header("Accept") accept: String,
        @Header("appKey") appKey: String,
        @Query("searchKeyword") searchKeyword: String
    ): TmapResponse
}

