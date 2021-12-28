package ru.intelligency.scholarship.data.portfolio

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.intelligency.scholarship.domain.portfolio.DocumentsRepository
import ru.intelligency.scholarship.domain.portfolio.model.Document
import ru.intelligency.scholarship.domain.portfolio.model.SimpleDate
import ru.intelligency.scholarship.presentation.utils.Status

class DocumentsRepositoryImpl : DocumentsRepository {

    override fun getAllDocuments(): Flow<List<Document>> = flow {
        emit(documentsList)
    }

    override fun getDocument(id: String): Flow<Document> = flow {
        val document = documentsList.firstOrNull { document -> document.id == id }
        document?.let {
            emit(it)
        }
    }

    override fun getDefaultEventTypes(): List<String> {
        return defaultEventTypes
    }

    override fun getDefaultEventStatuses(): List<String> {
        return defaultEventStatuses
    }

    private val documentsList = mutableListOf(
        Document(
            id = "0",
            title = "Сертификат",
            documentStatus = Status.IN_WAITING,
            eventType = "Хакатон",
            eventStatus = "Международное",
            dateOfReceipt = SimpleDate(1, 1, 2022),
            eventLocation = "Екатеринбург"
        ),
        Document(
            id = "1",
            title = "Диплом",
            documentStatus = Status.ACCEPTED,
            eventType = "Хакатон",
            eventStatus = "Международное",
            dateOfReceipt = SimpleDate(1, 1, 2022),
            eventLocation = "Екатеринбург"
        )
    )

    override fun saveDocument(document: Document) {
        if (documentsList.filter { it.id == document.id }.size > 1) {
            deleteDocument(document.id)
        }
        documentsList.add(document)
    }

    override fun deleteDocument(documentId: String) {
        documentsList.first { it.id == documentId }.apply {
            documentsList.remove(this)
        }
    }

    companion object {

        private val defaultEventTypes = listOf(
            "Хакатон",
            "Олимпиада",
            "Конференция",
            "Конкурс",
            "Соревнование",
            "Состязание",
            "Другое"
        )
        private val defaultEventStatuses =
            listOf("Международное", "Всероссийское", "Ведомственное", "Региональное", "Другое")
    }
}
