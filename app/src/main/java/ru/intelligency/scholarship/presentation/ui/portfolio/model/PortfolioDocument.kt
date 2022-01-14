package ru.intelligency.scholarship.presentation.ui.portfolio.model

import ru.intelligency.scholarship.presentation.utils.Status
import java.util.Date

data class PortfolioDocument(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val documentStatus: Status = Status.IN_WAITING,
    val dateOfReceipt: Long = Date().time,
    val fileName: String = ""
)
