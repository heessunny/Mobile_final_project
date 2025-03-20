package com.example.a01_20210778.data

data class TmapResponse(
    val searchPoiInfo: SearchPoiInfo
)

data class SearchPoiInfo(
    val pois: Pois
)

data class Pois(
    val poi: List<Poi>
)

data class Poi(
    val name: String,
    val frontLat: String, // 위도
    val frontLon: String  // 경도
)