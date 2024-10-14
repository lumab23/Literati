package com.aula.literatiapp.model

object NotificationDataProvider{
    val user =
        Notification(
            name = "Amanda",
            action = "curtiu",
            time = 40,
            userImageId = 0
        )

    val notificationList = listOf(
        user,
        Notification(
            name = "Luma",
            action = "curtiu",
            time = 40,
            userImageId = 2
        ),
        Notification(
            name = "Sofya",
            action = "curtiu",
            time = 40,
            userImageId = 1
        ),
        Notification(
            name = "Raquel",
            action = "curtiu",
            time = 40,
            userImageId = 3
        )
    )
}