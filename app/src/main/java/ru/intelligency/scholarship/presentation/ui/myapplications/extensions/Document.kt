package ru.intelligency.scholarship.presentation.ui.myapplications.extensions

import ru.intelligency.scholarship.domain.portfolio.model.Document
import ru.intelligency.scholarship.presentation.ui.myapplications.adapter.ApplicationDocument

fun Document.toApplicationDocument(): ApplicationDocument {
    return ApplicationDocument(
        id = id,
        name = title,
        description = "$eventType, $eventStatus",
        documentStatus = documentStatus,
        expirationDate = dateOfReceipt,
        fileName = fileName
    )
}
