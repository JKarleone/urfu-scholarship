package ru.intelligency.scholarship.data.myapplications

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.intelligency.scholarship.data.extensions.toCreateRequestModel
import ru.intelligency.scholarship.data.extensions.toDomainModel
import ru.intelligency.scholarship.data.extensions.toEntity
import ru.intelligency.scholarship.data.extensions.toStatus
import ru.intelligency.scholarship.data.profile.UserSharedPreferences
import ru.intelligency.scholarship.domain.myapplications.ApplicationsRepository
import ru.intelligency.scholarship.domain.myapplications.models.Application
import ru.intelligency.scholarship.domain.myapplications.models.ApplicationWithDocuments

class ApplicationsRepositoryImpl(
    private val applicationDao: ApplicationDao,
    private val applicationWithDocumentsDao: ApplicationDocumentCrossRefDao,
    private val applicationsApi: ApplicationsApi,
    private val userSharedPreferences: UserSharedPreferences
) : ApplicationsRepository {

    override fun getApplications(): Flow<List<Application>> {
        return applicationDao.getAllApplicationsFlow()
            .map { list ->
                list.map { applicationEntity -> applicationEntity.toDomainModel() }
            }
    }

    override suspend fun updateApplicationsStatuses() {
        val response = applicationsApi.getApplicationsStatuses()
        if (response.isSuccessful) {
            response.body()?.forEach { (id, status) ->
                applicationDao.getApplicationById(id)?.let { application ->
                    val statusEnum = status.toStatus()
                    val appWithNewStatus = application.copy(applicationStatus = statusEnum)
                    applicationDao.updateApplication(appWithNewStatus)
                }
            }
            val allIds = response.body()?.map { it.applicationId }
            applicationDao.getAllApplications().forEach { applicationEntity ->
                allIds?.let { ids ->
                    if (applicationEntity.applicationId !in ids) {
                        deleteLocalApplication(applicationEntity.applicationId)
                    }
                }
            }
        }
    }

    override fun getApplicationById(applicationId: Int): Flow<ApplicationWithDocuments?> {
        return applicationDao.getApplicationFlowById(applicationId).map { it?.toDomainModel() }
    }

    override suspend fun createApplication(application: Application, documentIds: List<Int>) {
//        val applicationId = applicationDao.createApplication(application.toEntity())
//        documentIds.forEach { documentId ->
//            applicationWithDocumentsDao.insertApplicationDocumentCrossRef(
//                ApplicationDocumentCrossRef(applicationId.toInt(), documentId)
//            )
//        }
        val response = applicationsApi.postApplication(
            application.toCreateRequestModel(documentIds, userSharedPreferences.getUserData().id)
        )
        if (response.isSuccessful) {
            response.body()?.let { body ->
                val appId = body.id
                applicationDao.createApplication(application.toEntity().copy(applicationId = appId))
                documentIds.forEach { documentId ->
                    applicationWithDocumentsDao.insertApplicationDocumentCrossRef(
                        ApplicationDocumentCrossRef(appId, documentId)
                    )
                }
            }
        }
    }

    override suspend fun updateApplication(application: Application) {
        applicationDao.updateApplication(application.toEntity())
    }

    override suspend fun deleteApplication(applicationId: Int) {
        deleteLocalApplication(applicationId)
        applicationsApi.deleteApplication(applicationId)
    }

    private suspend fun deleteLocalApplication(applicationId: Int) {
        applicationDao.deleteApplication(applicationId)
        applicationWithDocumentsDao.deleteApplicationWithId(applicationId)
    }
}
