package ru.intelligency.scholarship.presentation.extensions

import android.widget.ImageView
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.presentation.utils.Status

fun ImageView.setStatusIcon(status: Status) {
    val statusImage = when (status) {
        Status.ACCEPTED -> R.drawable.ic_accepted
        Status.IN_WAITING -> R.drawable.ic_waiting
        Status.REJECTED -> R.drawable.ic_rejected
    }
    setImageResource(statusImage)
}
