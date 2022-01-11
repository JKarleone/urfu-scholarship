package ru.intelligency.scholarship.presentation.ui.portfolio.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.intelligency.scholarship.data.portfolio.ImageProvider
import ru.intelligency.scholarship.domain.portfolio.PortfolioInteractor
import javax.inject.Inject

class NewDocumentViewModelFactory @Inject constructor(
    private val portfolioInteractor: PortfolioInteractor,
    private val imageProvider: ImageProvider
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewDocumentViewModel::class.java)) {
            return NewDocumentViewModel(portfolioInteractor, imageProvider) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
