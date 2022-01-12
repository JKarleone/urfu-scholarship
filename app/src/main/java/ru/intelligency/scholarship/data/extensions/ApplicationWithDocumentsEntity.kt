package ru.intelligency.scholarship.data.extensions

import ru.intelligency.scholarship.data.myapplications.ApplicationWithDocumentsEntity
import ru.intelligency.scholarship.domain.myapplications.models.ApplicationWithDocuments

fun ApplicationWithDocumentsEntity.toDomainModel(): ApplicationWithDocuments {
    return ApplicationWithDocuments(
        application = application.toDomainModel(),
        documents = documents.map { it.toDomainModel() }
    )
}
