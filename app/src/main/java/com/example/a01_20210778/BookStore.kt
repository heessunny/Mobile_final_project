package com.example.a01_20210778

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class StoreRoot(
    @SerializedName("itemOffStoreList") val stores: List<StoreData> // items가 제대로 매핑되어야 함
)
@Parcelize
data class StoreData(
    @SerializedName("offName") val name: String = "",
): Parcelable