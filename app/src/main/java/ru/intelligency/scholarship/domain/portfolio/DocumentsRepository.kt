package ru.intelligency.scholarship.domain.portfolio

import kotlinx.coroutines.flow.Flow
import ru.intelligency.scholarship.domain.portfolio.model.Document

interface DocumentsRepository {

    fun getAllDocuments(): Flow<List<Document>>

    fun getDocument(id: Int): Flow<Document>
}
