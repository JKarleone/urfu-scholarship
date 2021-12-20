package ru.intelligency.scholarship.presentation.ui.portfolio.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import ru.intelligency.scholarship.domain.portfolio.PortfolioInteractor

class PortfolioViewModel(
    private val portfolioInteractor: PortfolioInteractor
) : ViewModel() {

    val documents = portfolioInteractor.getAllDocuments()
        .stateIn(viewModelScope, SharingStarted.Lazily, listOf())
}
