package ru.intelligency.scholarship.domain.myapplications

import kotlinx.coroutines.flow.Flow
import ru.intelligency.scholarship.domain.myapplications.models.Application
import ru.intelligency.scholarship.domain.myapplications.models.ApplicationWithDocuments

class ApplicationsInteractor(
    private val repository: ApplicationsRepository
) {

    fun getApplications(): Flow<List<Application>> {
        return repository.getApplications()
    }

    fun getApplication(applicationId: Int): Flow<ApplicationWithDocuments?> {
        return repository.getApplicationById(applicationId)
    }

    suspend fun saveApplication(application: Application, documentIds: List<Int>) {
        repository.createApplication(application, documentIds)
    }

    suspend fun deleteApplication(applicationId: Int) {
        repository.deleteApplication(applicationId)
    }
}
