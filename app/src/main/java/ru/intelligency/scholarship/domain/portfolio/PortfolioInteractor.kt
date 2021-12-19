package ru.intelligency.scholarship.domain.portfolio

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.domain.portfolio.extensions.toPortfolioDocument
import ru.intelligency.scholarship.domain.portfolio.model.Document
import ru.intelligency.scholarship.domain.portfolio.model.SimpleDate
import ru.intelligency.scholarship.presentation.ui.portfolio.model.PortfolioDocument

class PortfolioInteractor(
    private val documentsRepository: DocumentsRepository,
    private val context: Context
) {

    fun getAllDocuments(): Flow<List<PortfolioDocument>> {
        return documentsRepository.getAllDocuments().map { list ->
            list.map { document -> document.toPortfolioDocument() }
        }
    }

    fun getDocument(id: Int): Flow<Document> {
        return documentsRepository.getDocument(id)
    }

    fun getModifiedReceiptDate(receiptDate: SimpleDate): String {
        return context.getString(
            R.string.document_details_receipt_date,
            receiptDate.day,
            receiptDate.month,
            receiptDate.year,
            receiptDate.day,
            receiptDate.month,
            receiptDate.year + 2
        )
    }
}
