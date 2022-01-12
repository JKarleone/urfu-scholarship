package ru.intelligency.scholarship.presentation.ui.myapplications.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.intelligency.scholarship.domain.myapplications.ApplicationsInteractor
import ru.intelligency.scholarship.domain.myapplications.models.Application
import ru.intelligency.scholarship.domain.myapplications.models.ApplicationWithDocuments
import ru.intelligency.scholarship.domain.portfolio.PortfolioInteractor
import ru.intelligency.scholarship.presentation.ui.myapplications.adapter.ApplicationDocument
import ru.intelligency.scholarship.presentation.ui.myapplications.extensions.toApplicationDocument

class ApplicationsViewModel(
    private val applicationsInteractor: ApplicationsInteractor,
    portfolioInteractor: PortfolioInteractor
) : ViewModel() {

    val applications: StateFlow<List<Application>> = applicationsInteractor.getApplications()
        .stateIn(viewModelScope, SharingStarted.Lazily, listOf())

    val documents: StateFlow<List<ApplicationDocument>> = portfolioInteractor.getAllDocuments()
        .map { list -> list.map { it.toApplicationDocument() } }
        .stateIn(viewModelScope, SharingStarted.Lazily, listOf())

    suspend fun getApplication(id: Long): StateFlow<ApplicationWithDocuments?> {
        return applicationsInteractor.getApplication(id)
            .stateIn(viewModelScope)
    }

    suspend fun saveApplication(application: Application) {
        applicationsInteractor.saveApplication(application)
    }

    suspend fun deleteApplication(applicationId: Long) {
        applicationsInteractor.deleteApplication(applicationId)
    }
}
