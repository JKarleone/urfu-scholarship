package ru.intelligency.scholarship.domain.portfolio

import kotlinx.coroutines.flow.Flow
import ru.intelligency.scholarship.domain.portfolio.model.Document

interface DocumentsRepository {

    fun getAllDocuments(): Flow<List<Document>>

    fun getDocument(id: Long): Flow<Document?>

    fun getDefaultEventTypes(): List<String>

    fun getDefaultEventStatuses(): List<String>

    suspend fun createDocument(document: Document)

    suspend fun updateDocument(document: Document)

    suspend fun deleteDocument(documentId: Long)
}
