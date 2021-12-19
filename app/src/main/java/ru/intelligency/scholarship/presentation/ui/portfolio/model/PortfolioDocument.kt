package ru.intelligency.scholarship.presentation.ui.portfolio.model

import ru.intelligency.scholarship.domain.portfolio.model.SimpleDate
import ru.intelligency.scholarship.presentation.utils.DocumentStatus

data class PortfolioDocument(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val documentStatus: DocumentStatus = DocumentStatus.IN_WAITING,
    val expirationDate: SimpleDate = SimpleDate(1, 1, 1970)
)
