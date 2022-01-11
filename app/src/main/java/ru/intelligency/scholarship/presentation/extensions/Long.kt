package ru.intelligency.scholarship.presentation.extensions

import java.text.SimpleDateFormat

fun Long.getStringDate(): String {
    return SimpleDateFormat("dd.MM.yyyy").format(this)
}
