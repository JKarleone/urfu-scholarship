package ru.intelligency.scholarship.presentation.ui.portfolio.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.intelligency.scholarship.domain.portfolio.PortfolioInteractor
import ru.intelligency.scholarship.presentation.ui.portfolio.extensions.toPortfolioDocument

class PortfolioViewModel(
    private val portfolioInteractor: PortfolioInteractor
) : ViewModel() {

    val documents = portfolioInteractor.getAllDocuments()
        .map { list -> list.map { it.toPortfolioDocument() } }
        .stateIn(viewModelScope, SharingStarted.Lazily, listOf())

    suspend fun updateDocumentsStatuses() {
        portfolioInteractor.updateDocumentsStatuses()
    }
}
