package com.example.a01_20210778.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert
    fun insertBook(book: Book)

    @Delete
    fun deleteBook(book: Book)

    @Query("SELECT * FROM book_storage")
    fun getAllBooks() : Flow<List<Book>>

    @Query("SELECT * FROM book_storage LIMIT 4")
    fun getTop4Books(): Flow<List<Book>>

}