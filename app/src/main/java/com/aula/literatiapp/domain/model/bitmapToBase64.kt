package com.aula.literatiapp.domain.model

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream
import android.util.Base64

fun bitmapToBase64(bitmap: Bitmap): String {
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.NO_WRAP)
}
