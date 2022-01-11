package ru.intelligency.scholarship.data.extensions

import ru.intelligency.scholarship.data.portfolio.DocumentEntity
import ru.intelligency.scholarship.domain.portfolio.model.Document

fun Document.toEntity(): DocumentEntity {
    return DocumentEntity(
        documentId = id,
        title = title,
        documentStatus = documentStatus,
        eventType = eventType,
        eventStatus = eventStatus,
        eventPlace = eventLocation,
        dateOfReceipt = dateOfReceipt,
        fileName = fileName
    )
}
