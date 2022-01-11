package ru.intelligency.scholarship.domain.myapplications

import kotlinx.coroutines.flow.Flow
import ru.intelligency.scholarship.domain.myapplications.models.Application

class ApplicationsInteractor(
    private val repository: ApplicationsRepository
) {

    fun getApplications(): Flow<List<Application>> {
        return repository.getApplications()
    }

    fun getApplication(id: Long): Flow<Application> {
        return repository.getApplicationById(id)
    }

    fun saveApplication(application: Application) {
        repository.saveApplication(application)
    }

    fun deleteApplication(applicationId: Long) {
        repository.deleteApplication(applicationId)
    }
}
