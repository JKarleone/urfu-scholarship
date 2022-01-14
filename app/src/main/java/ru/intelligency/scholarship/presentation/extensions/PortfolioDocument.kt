package ru.intelligency.scholarship.presentation.extensions

import android.content.Context
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.presentation.ui.portfolio.model.PortfolioDocument
import ru.intelligency.scholarship.presentation.utils.Status
import java.util.Calendar

fun PortfolioDocument.getStatusText(context: Context): String {
    return when (documentStatus) {
        Status.ACCEPTED -> {
            context.getString(
                R.string.portfolio_profile_expiration_date,
                Calendar.getInstance().apply {
                    timeInMillis = expirationDate
                    set(Calendar.YEAR, get(Calendar.YEAR) + 2)
                }.timeInMillis.getStringDate()
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
