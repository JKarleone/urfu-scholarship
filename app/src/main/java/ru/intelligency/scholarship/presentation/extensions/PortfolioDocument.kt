package ru.intelligency.scholarship.presentation.extensions

import android.content.Context
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.presentation.ui.portfolio.model.PortfolioDocument
import ru.intelligency.scholarship.presentation.utils.Status

fun PortfolioDocument.getStatusText(context: Context): String {
    return when (documentStatus) {
        Status.ACCEPTED -> {
            context.getString(
                R.string.portfolio_profile_expiration_date,
                expirationDate.getStringDate()
            )
        }
        Status.IN_WAITING -> {
            "Ожидает верификации"
        }
        Status.REJECTED -> {
            "Срок действия истек"
        }
    }
}
