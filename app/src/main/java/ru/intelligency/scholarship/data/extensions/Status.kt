package ru.intelligency.scholarship.data.extensions

import ru.intelligency.scholarship.presentation.utils.Status

fun Status.toInt(): Int {
    return when (this) {
        Status.REJECTED -> 0
        Status.ACCEPTED -> 1
        else -> 2
    }
}

fun Int.toStatus(): Status {
    return when (this) {
        0 -> Status.REJECTED
        1 -> Status.ACCEPTED
        else -> Status.IN_WAITING
    }
}
