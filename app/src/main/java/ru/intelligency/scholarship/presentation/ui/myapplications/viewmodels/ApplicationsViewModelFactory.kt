package ru.intelligency.scholarship.presentation.ui.myapplications.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.intelligency.scholarship.domain.myapplications.ApplicationsInteractor

class ApplicationsViewModelFactory(
    private val applicationsInteractor: ApplicationsInteractor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApplicationsViewModel::class.java)) {
            return ApplicationsViewModel(applicationsInteractor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
