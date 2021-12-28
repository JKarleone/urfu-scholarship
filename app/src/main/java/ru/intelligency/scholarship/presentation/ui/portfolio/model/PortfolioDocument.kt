package ru.intelligency.scholarship.presentation.ui.portfolio.model

import ru.intelligency.scholarship.domain.portfolio.model.SimpleDate
import ru.intelligency.scholarship.presentation.utils.Status
import java.util.*

data class PortfolioDocument(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    val description: String = "",
    val documentStatus: Status = Status.IN_WAITING,
    val expirationDate: SimpleDate = SimpleDate(1, 1, 1970)
)
