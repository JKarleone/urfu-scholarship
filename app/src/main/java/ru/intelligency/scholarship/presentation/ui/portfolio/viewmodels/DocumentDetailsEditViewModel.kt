package ru.intelligency.scholarship.presentation.ui.portfolio.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import ru.intelligency.scholarship.domain.portfolio.PortfolioInteractor
import ru.intelligency.scholarship.domain.portfolio.model.Document

class DocumentDetailsEditViewModel(
    private val interactor: PortfolioInteractor,
    id: Long
) : ViewModel() {

    val document: StateFlow<Document?> = interactor.getDocument(id)
        .stateIn(viewModelScope, SharingStarted.Lazily, Document())

    fun getDefaultEventTypes(): List<String> {
        return interactor.getDefaultEventTypes()
    }

    fun getDefaultEventStatuses(): List<String> {
        return interactor.getDefaultEventStatuses()
    }

    suspend fun createDocument(document: Document) {
        interactor.createDocument(document)
    }

    suspend fun updateDocument(document: Document) {
        interactor.updateDocument(document)
    }

    suspend fun deleteDocument(documentId: Long) {
        interactor.deleteDocument(documentId)
    }
}
