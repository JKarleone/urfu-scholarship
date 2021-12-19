package ru.intelligency.scholarship.data.portfolio

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.intelligency.scholarship.domain.portfolio.DocumentsRepository
import ru.intelligency.scholarship.domain.portfolio.model.Document
import ru.intelligency.scholarship.domain.portfolio.model.SimpleDate
import ru.intelligency.scholarship.presentation.utils.DocumentStatus

class DocumentsRepositoryImpl : DocumentsRepository {

    override fun getAllDocuments(): Flow<List<Document>> = flow {
        emit(documentsList)
    }

    override fun getDocument(id: Int): Flow<Document> = flow {
        val document = documentsList.firstOrNull { document -> document.id == id }
        document?.let {
            emit(it)
        }
    }

    private val documentsList = listOf(
        Document(
            id = 0,
            title = "Сертификат",
            documentStatus = DocumentStatus.IN_WAITING,
            eventType = "Хакатон",
            eventStatus = "Международный",
            dateOfReceipt = SimpleDate(1, 1, 2022),
            eventLocation = "Екатеринбург"
        ),
        Document(
            id = 1,
            title = "Диплом",
            documentStatus = DocumentStatus.ACCEPTED,
            eventType = "Хакатон",
            eventStatus = "Международный",
            dateOfReceipt = SimpleDate(1, 1, 2022),
            eventLocation = "Екатеринбург"
        )
    )
}
