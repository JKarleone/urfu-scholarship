package ru.intelligency.scholarship.domain.portfolio.model

import ru.intelligency.scholarship.presentation.utils.DocumentStatus

data class Document(
    val id: Int,
    val title: String,
    val documentStatus: DocumentStatus,
    val eventType: String,
    val eventStatus: String,
    val dateOfReceipt: SimpleDate,
    val venueOfEvent: String
)
