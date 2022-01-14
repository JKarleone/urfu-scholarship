package ru.intelligency.scholarship.presentation.ui.portfolio.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.intelligency.scholarship.domain.portfolio.PortfolioInteractor

class DocumentDetailsViewModelFactory(
    private val interactor: PortfolioInteractor,
    private val id: Int
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DocumentDetailsViewModel::class.java)) {
            return DocumentDetailsViewModel(interactor, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
