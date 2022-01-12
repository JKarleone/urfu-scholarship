package ru.intelligency.scholarship.presentation.extensions

import android.content.Context
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.presentation.ui.myapplications.adapter.ApplicationDocument
import ru.intelligency.scholarship.presentation.utils.Status

fun ApplicationDocument.getStatusText(context: Context): String {
    return when (documentStatus) {
        Status.ACCEPTED -> {
            context.getString(
                R.string.portfolio_profile_expiration_date,
                expirationDate.getStringDate()
            )
        }
        Status.IN_WAITING -> {
            context.getString(R.string.document_awaiting)
        }
        Status.REJECTED -> {
            context.getString(R.string.document_rejected)
        }
    }
}
