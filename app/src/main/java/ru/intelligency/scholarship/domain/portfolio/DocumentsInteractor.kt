package ru.intelligency.scholarship.domain.portfolio

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.intelligency.scholarship.domain.portfolio.extensions.toPortfolioDocument
import ru.intelligency.scholarship.presentation.ui.portfolio.model.PortfolioDocument

class DocumentsInteractor(private val documentsRepository: DocumentsRepository) {

    fun getAllDocuments(): Flow<List<PortfolioDocument>> {
        return documentsRepository.getAllDocuments().map { list ->
            list.map { document -> document.toPortfolioDocument() }
        }
    }
}
