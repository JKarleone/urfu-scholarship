package ru.intelligency.scholarship.presentation.ui.portfolio.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.intelligency.scholarship.domain.portfolio.PortfolioInteractor

class PortfolioViewModelFactory(
    private val interactor: PortfolioInteractor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PortfolioViewModel::class.java)) {
            return PortfolioViewModel(interactor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
