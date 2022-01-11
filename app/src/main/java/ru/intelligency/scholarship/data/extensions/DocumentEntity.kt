package ru.intelligency.scholarship.data.extensions

import ru.intelligency.scholarship.data.portfolio.DocumentEntity
import ru.intelligency.scholarship.domain.portfolio.model.Document

fun DocumentEntity.toDomainModel(): Document {
    return Document(
        documentId,
        title,
        documentStatus,
        eventType,
        eventStatus,
        dateOfReceipt,
        eventPlace,
        fileName
    )
}
