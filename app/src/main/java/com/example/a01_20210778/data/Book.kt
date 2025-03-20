package com.example.a01_20210778.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.concurrent.Flow.Publisher

@Entity (tableName = "book_storage")
data class Book(
    @PrimaryKey (autoGenerate = true)
    val _id: Int,

    @ColumnInfo(name="title")
    var title: String?,

    @ColumnInfo(name="author")
    var author: String?,

    @ColumnInfo(name="publisher")
    var publisher: String?,

    @ColumnInfo(name="description")
    var description: String?,

    @ColumnInfo(name="isbn")
    var isbn: String?,

    @ColumnInfo(name="cover")
    var cover: String?,


    )
