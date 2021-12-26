package ru.intelligency.scholarship.presentation.extensions

import ru.intelligency.scholarship.domain.portfolio.model.SimpleDate

fun SimpleDate.getStringDate(): String {
    val dayStr = if (day < 10) "0$day" else day.toString()
    val monthStr = if (month < 10) "0$month" else month.toString()
    return "$dayStr.$monthStr.$year"
}
