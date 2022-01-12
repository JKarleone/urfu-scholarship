package ru.intelligency.scholarship.domain.portfolio

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.domain.portfolio.model.Document
import ru.intelligency.scholarship.presentation.extensions.getStringDate
import ru.intelligency.scholarship.presentation.ui.portfolio.extensions.toPortfolioDocument
import ru.intelligency.scholarship.presentation.ui.portfolio.model.PortfolioDocument
import java.util.Calendar

class PortfolioInteractor(
    private val documentsRepository: DocumentsRepository,
    private val context: Context
) {

    fun getAllDocuments(): Flow<List<PortfolioDocument>> {
        return documentsRepository.getAllDocuments().map { list ->
            list.map { document -> document.toPortfolioDocument() }
        }
    }

    fun getDocument(id: Long): Flow<Document?> {
        return documentsRepository.getDocument(id)
    }

    fun getModifiedReceiptDate(receiptDate: Long): String {
        val date = Calendar.getInstance().apply {
            timeInMillis = receiptDate
            set(Calendar.YEAR, get(Calendar.YEAR) + 2)
        }
        return context.getString(
            R.string.document_details_receipt_date,
            receiptDate.getStringDate(),
            date.timeInMillis.getStringDate()
        )
    }

    fun getDefaultEventTypes(): List<String> {
        return documentsRepository.getDefaultEventTypes()
    }

    fun getDefaultEventStatuses(): List<String> {
        return documentsRepository.getDefaultEventStatuses()
    }

    suspend fun createDocument(document: Document) {
        documentsRepository.createDocument(document)
    }

    suspend fun updateDocument(document: Document) {
        documentsRepository.updateDocument(document)
    }

    suspend fun deleteDocument(documentId: Long) {
        documentsRepository.deleteDocument(documentId)
    }
}
