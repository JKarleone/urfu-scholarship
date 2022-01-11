package ru.intelligency.scholarship.presentation.ui.portfolio.fragments

import androidx.lifecycle.ViewModel
import ru.intelligency.scholarship.domain.portfolio.PortfolioInteractor
import ru.intelligency.scholarship.domain.portfolio.model.Document

class NewDocumentViewModel(
    private val interactor: PortfolioInteractor
) : ViewModel() {

    fun getDefaultEventTypes(): List<String> {
        return interactor.getDefaultEventTypes()
    }

    fun getDefaultEventStatuses(): List<String> {
        return interactor.getDefaultEventStatuses()
    }

    suspend fun createDocument(document: Document) {
        interactor.createDocument(document)
    }
}
