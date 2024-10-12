package com.aula.literatiapp.model

data class Message(
    val username: String,
    val profileImageUrl: String,
    val body: String,
    val time: String,
    val isSentByUser: Boolean
)
