package ru.intelligency.scholarship.domain.portfolio.extensions

import ru.intelligency.scholarship.domain.portfolio.model.Document
import ru.intelligency.scholarship.presentation.ui.portfolio.model.PortfolioDocument

fun Document.toPortfolioDocument(): PortfolioDocument {
    return PortfolioDocument(
        id = id,
        name = title,
        description = "$eventType, $eventStatus",
        documentStatus = documentStatus,
        expirationDate = dateOfReceipt
    )
}
