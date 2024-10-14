package com.aula.literatiapp.model

data class Notification(
    val name:String, val action:String, val time:Int, val userImageId: Int = 0
)