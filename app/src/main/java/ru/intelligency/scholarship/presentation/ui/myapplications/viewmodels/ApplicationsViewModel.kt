package ru.intelligency.scholarship.presentation.ui.myapplications.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import ru.intelligency.scholarship.domain.myapplications.ApplicationsInteractor
import ru.intelligency.scholarship.domain.myapplications.models.Application

class ApplicationsViewModel(
    private val applicationsInteractor: ApplicationsInteractor
) : ViewModel() {

    val applications: StateFlow<List<Application>> = applicationsInteractor.getApplications()
        .stateIn(viewModelScope, SharingStarted.Lazily, listOf())

    suspend fun getApplication(id: String): StateFlow<Application> {
        return applicationsInteractor.getApplication(id)
            .stateIn(viewModelScope)
    }
}
