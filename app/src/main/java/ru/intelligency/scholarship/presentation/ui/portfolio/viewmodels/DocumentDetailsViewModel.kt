package ru.intelligency.scholarship.presentation.ui.portfolio.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import ru.intelligency.scholarship.domain.portfolio.PortfolioInteractor
import ru.intelligency.scholarship.domain.portfolio.model.Document

class DocumentDetailsViewModel(
    private val interactor: PortfolioInteractor,
    id: Int
) : ViewModel() {

    val document: StateFlow<Document> = interactor.getDocument(id)
        .stateIn(viewModelScope, SharingStarted.Lazily, Document())

    fun getModifiedReceiptDateString(document: Document): String {
        return interactor.getModifiedReceiptDate(document.dateOfReceipt)
    }
}
