package ru.intelligency.scholarship.presentation.ui.myapplications.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.intelligency.scholarship.domain.myapplications.ApplicationsInteractor
import ru.intelligency.scholarship.domain.portfolio.PortfolioInteractor
import ru.intelligency.scholarship.domain.profile.ProfileInteractor

class ApplicationsViewModelFactory(
    private val applicationsInteractor: ApplicationsInteractor,
    private val portfolioInteractor: PortfolioInteractor,
    private val profileInteractor: ProfileInteractor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApplicationsViewModel::class.java)) {
            return ApplicationsViewModel(
                applicationsInteractor,
                portfolioInteractor,
                profileInteractor
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
