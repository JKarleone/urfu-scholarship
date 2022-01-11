package ru.intelligency.scholarship.domain.myapplications

import kotlinx.coroutines.flow.Flow
import ru.intelligency.scholarship.domain.myapplications.models.Application

interface ApplicationsRepository {

    fun getApplications(): Flow<List<Application>>

    fun getApplicationById(id: Long): Flow<Application>

    fun saveApplication(application: Application)

    fun deleteApplication(applicationId: Long)
}
