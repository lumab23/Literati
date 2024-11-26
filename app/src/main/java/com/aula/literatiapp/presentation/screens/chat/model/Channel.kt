package com.aula.literatiapp.presentation.screens.chat.model

data class Channel(
    val id: String = "",
    val name: String,
    val createdAt: Long = System.currentTimeMillis()
)