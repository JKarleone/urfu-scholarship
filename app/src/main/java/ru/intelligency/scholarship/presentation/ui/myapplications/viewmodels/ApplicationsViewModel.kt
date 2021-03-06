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
import ru.intelligency.scholarship.domain.profile.ProfileInteractor
import ru.intelligency.scholarship.domain.profile.models.Profile
import ru.intelligency.scholarship.presentation.ui.myapplications.adapter.ApplicationDocument
import ru.intelligency.scholarship.presentation.ui.myapplications.extensions.toApplicationDocument

class ApplicationsViewModel(
    private val applicationsInteractor: ApplicationsInteractor,
    portfolioInteractor: PortfolioInteractor,
    profileInteractor: ProfileInteractor
) : ViewModel() {

    val applications: StateFlow<List<Application>> = applicationsInteractor.getApplications()
        .stateIn(viewModelScope, SharingStarted.Lazily, listOf())

    val documents: StateFlow<List<ApplicationDocument>> =
        portfolioInteractor.getAllAcceptedDocuments()
            .map { list -> list.map { it.toApplicationDocument() } }
            .stateIn(viewModelScope, SharingStarted.Lazily, listOf())

    val profile: StateFlow<Profile> = profileInteractor.getProfileFlow()
        .stateIn(viewModelScope, SharingStarted.Lazily, Profile())

    suspend fun getApplication(applicationId: Int): StateFlow<ApplicationWithDocuments?> {
        return applicationsInteractor.getApplication(applicationId)
            .stateIn(viewModelScope)
    }

    suspend fun updateApplicationStatuses() {
        applicationsInteractor.updateApplicationsStatuses()
    }

    suspend fun saveApplication(application: Application, documentIds: List<Int>) {
        applicationsInteractor.saveApplication(application, documentIds)
    }

    suspend fun deleteApplication(applicationId: Int) {
        applicationsInteractor.deleteApplication(applicationId)
    }
}
