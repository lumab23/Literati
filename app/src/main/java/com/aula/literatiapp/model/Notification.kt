package com.aula.literatiapp.model

data class Notification(
    val name: String,
    val action: String,
    val time: String,
    val userImageUrl: String,
    val nomeLivroOuComunidade: String
    // adicionar depois
    //val community: Community,
    //val book: Book
)
