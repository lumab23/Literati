package com.aula.literatiapp.data.remote

import com.aula.literatiapp.domain.model.BookItem
import com.aula.literatiapp.domain.model.BookResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GoogleBooksApi {

    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("key") apiKey: String,
        @Query("langRestrict") langRestrict: String? = null,
        @Query("maxResults") maxResults: Int = 40
    ): BookResponse

    @GET("volumes/{bookId}")
    suspend fun getBookById(
        @Path("bookId") volumeId: String,
        @Query("key") apiKey: String
    ): BookItem


}
