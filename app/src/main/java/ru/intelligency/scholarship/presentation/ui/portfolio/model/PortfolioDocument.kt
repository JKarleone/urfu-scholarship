package ru.intelligency.scholarship.presentation.ui.portfolio.model

import ru.intelligency.scholarship.presentation.utils.DocumentStatus
import java.util.*

data class PortfolioDocument(
    val id: Int,
    val name: String,
    val description: String,
    val status: DocumentStatus,
    val expirationDate: Date
)
