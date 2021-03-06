package ru.intelligency.scholarship.presentation.extensions

import android.util.Patterns
import java.text.SimpleDateFormat
import java.util.Calendar

fun String.matchEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.matchDate(): Boolean {
    val format = SimpleDateFormat("dd.MM.yyyy")
    return try {
        format.parse(this)
        true
    } catch (e: Exception) {
        false
    }
}

fun String.matchDateByExpiration(): Boolean {
    val format = SimpleDateFormat("dd.MM.yyyy")
    return try {
        !format.parse(this).time.isExpired()
    } catch (e: Exception) {
        false
    }
}

fun String.toDate(): Long {
    val format = SimpleDateFormat("dd.MM.yyyy")
    return try {
        format.parse(this).time
    } catch (e: Exception) {
        Calendar.getInstance().timeInMillis
    }
}
