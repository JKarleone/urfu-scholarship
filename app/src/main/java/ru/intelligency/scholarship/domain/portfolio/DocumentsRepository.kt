package ru.intelligency.scholarship.domain.portfolio

import kotlinx.coroutines.flow.Flow
import ru.intelligency.scholarship.domain.portfolio.model.Document

interface DocumentsRepository {

    fun getAllDocuments(): Flow<List<Document>>

    suspend fun updateDocumentsStatuses()

    fun getDocument(documentId: Int): Flow<Document?>

    fun getDefaultEventTypes(): List<String>

    fun getDefaultEventStatuses(): List<String>

    suspend fun createDocument(document: Document)

    suspend fun updateDocument(document: Document)

    suspend fun deleteDocument(documentId: Int)
}
