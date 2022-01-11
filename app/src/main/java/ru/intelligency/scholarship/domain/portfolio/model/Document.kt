package ru.intelligency.scholarship.domain.portfolio.model

import ru.intelligency.scholarship.presentation.utils.Status
import java.util.Date

data class Document(
    val id: Long = 0,
    val title: String = "",
    val documentStatus: Status = Status.IN_WAITING,
    val eventType: String = "",
    val eventStatus: String = "",
    val dateOfReceipt: Long = Date().time,
    val eventLocation: String = "",
    val fileName: String = ""
)
