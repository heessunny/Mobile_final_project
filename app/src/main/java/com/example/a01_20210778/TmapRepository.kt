package com.example.a01_20210778

import com.example.a01_20210778.data.Poi

class TmapRepository (private val tmapService: TmapService) {

    suspend fun getInfo(keyWord: String): Poi? {
        return tmapService.getInfo(keyWord)
    }
}