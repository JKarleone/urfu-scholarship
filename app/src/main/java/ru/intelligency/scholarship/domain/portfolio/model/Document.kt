package ru.intelligency.scholarship.domain.portfolio.model

import ru.intelligency.scholarship.presentation.utils.DocumentStatus

data class Document(
    val id: Int = 0,
    val title: String = "",
    val documentStatus: DocumentStatus = DocumentStatus.IN_WAITING,
    val eventType: String = "",
    val eventStatus: String = "",
    val dateOfReceipt: SimpleDate = SimpleDate(1, 1, 1970),
    val eventLocation: String = "",
    val fileName: String = ""
)
