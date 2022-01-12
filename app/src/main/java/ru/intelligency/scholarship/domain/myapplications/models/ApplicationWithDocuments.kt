package ru.intelligency.scholarship.domain.myapplications.models

import ru.intelligency.scholarship.domain.portfolio.model.Document

data class ApplicationWithDocuments(
    val application: Application,
    val documents: List<Document>
)
