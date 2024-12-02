package com.aula.literatiapp.domain.model

data class Tag(
    val name: String,
    val bookIds: List<String> = emptyList()
)
