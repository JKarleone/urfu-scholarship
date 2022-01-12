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

    fun getApplication(applicationId: Long): Flow<ApplicationWithDocuments?> {
        return repository.getApplicationById(applicationId)
    }

    suspend fun saveApplication(application: Application) {
        repository.createApplication(application)
    }

    suspend fun deleteApplication(applicationId: Long) {
        repository.deleteApplication(applicationId)
    }
}
