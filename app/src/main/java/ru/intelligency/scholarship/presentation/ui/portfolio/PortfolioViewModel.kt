package ru.intelligency.scholarship.presentation.ui.portfolio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import ru.intelligency.scholarship.domain.portfolio.DocumentsInteractor

class PortfolioViewModel(
    private val documentsInteractor: DocumentsInteractor
) : ViewModel() {

    val documents = documentsInteractor.getAllDocuments()
        .stateIn(viewModelScope, SharingStarted.Lazily, listOf())
}
