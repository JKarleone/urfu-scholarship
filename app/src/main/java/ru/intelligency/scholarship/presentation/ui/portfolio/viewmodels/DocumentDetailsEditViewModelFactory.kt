package ru.intelligency.scholarship.presentation.ui.portfolio.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.intelligency.scholarship.domain.portfolio.PortfolioInteractor

class DocumentDetailsEditViewModelFactory(
    private val interactor: PortfolioInteractor,
    private val id: Int
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DocumentDetailsEditViewModel::class.java)) {
            return DocumentDetailsEditViewModel(interactor, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
