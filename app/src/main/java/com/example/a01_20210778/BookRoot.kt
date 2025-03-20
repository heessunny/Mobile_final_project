package com.example.a01_20210778

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Root(
    @SerializedName("title") val title: String,
    @SerializedName("item") val books: List<BookData> // items가 제대로 매핑되어야 함
)
@Parcelize
data class BookData(
    @SerializedName("itemId") val id: Long = 0,
    @SerializedName("title") val title: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("author") val author: String = "",
    @SerializedName("isbn13") val isbn13: String = "",
    @SerializedName("publisher") val publisher: String = "",
    @SerializedName("cover") val coverSmallUrl: String = ""
):Parcelable