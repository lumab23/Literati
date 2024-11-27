package com.aula.literatiapp.data.remote

import com.aula.literatiapp.domain.model.Book
import com.aula.literatiapp.domain.model.BookItem
import com.aula.literatiapp.domain.model.BookResponse
import org.intellij.lang.annotations.Language
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GoogleBooksApi {

    @GET("volumes")
    suspend fun searchBooks(
        @Query("q") query: String,
        @Query("key") apiKey: String
    ): BookResponse


}