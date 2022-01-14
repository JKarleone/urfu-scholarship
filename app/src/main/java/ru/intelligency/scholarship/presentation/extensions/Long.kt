package ru.intelligency.scholarship.presentation.extensions

import java.text.SimpleDateFormat
import java.util.Calendar

fun Long.getStringDate(): String {
    return SimpleDateFormat("dd.MM.yyyy").format(this)
}

fun Long.isExpired(): Boolean {
    val expirationDate = Calendar.getInstance().apply {
        timeInMillis = this@isExpired
        set(Calendar.YEAR, get(Calendar.YEAR) + 2)
    }.timeInMillis
    val currentDate = Calendar.getInstance().timeInMillis
    return expirationDate < currentDate
}
