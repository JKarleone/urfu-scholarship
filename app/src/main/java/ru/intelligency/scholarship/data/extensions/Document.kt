package ru.intelligency.scholarship.data.extensions

import ru.intelligency.scholarship.data.portfolio.CreateDocumentRequestModel
import ru.intelligency.scholarship.data.portfolio.DocumentEntity
import ru.intelligency.scholarship.data.portfolio.DocumentRequestModel
import ru.intelligency.scholarship.domain.portfolio.model.Document
import ru.intelligency.scholarship.presentation.extensions.getStringDate

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

fun Document.toCreateRequestModel(
    studentName: String
): CreateDocumentRequestModel {
    return CreateDocumentRequestModel(
        status = documentStatus.toInt(),
        title = title,
        eventType = eventType,
        eventStatus = eventStatus,
        eventPlace = eventLocation,
        dateOfReceipt = dateOfReceipt.getStringDate(),
        name = studentName,
        fileName = fileName,
    )
}

fun Document.toRequestModel(studentName: String): DocumentRequestModel {
    return DocumentRequestModel(
        id = id,
        status = documentStatus.toInt(),
        title = title,
        name = studentName,
        eventType = eventType,
        eventStatus = eventStatus,
        dateOfReceipt = dateOfReceipt.getStringDate(),
        eventPlace = eventLocation
    )
}
