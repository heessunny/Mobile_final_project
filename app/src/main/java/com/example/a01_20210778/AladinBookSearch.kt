package com.example.a01_20210778

import retrofit2.http.GET
import retrofit2.http.Query

interface AladinBookSearch {

    @GET("ItemSearch.aspx")
    suspend fun getBooksByName(
        @Query("ttbkey") apiKey: String, // Aladin API 키
        @Query("Query") query: String, // 검색어
        @Query("QueryType") queryType: String = "Title", // 기본값은 Title
        @Query("MaxResults") maxResults: Int = 100, // 최대 결과 수
        @Query("start") start: Int = 1, // 검색 시작 위치
        @Query("SearchTarget") searchTarget: String = "Book", // 검색 대상
        @Query("output") output: String = "js", // 출력 형식
        @Query("Version") version: String = "20131101" // API 버전
    ): Root // Aladin API에 맞는 응답 DTO


}