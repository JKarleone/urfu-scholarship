package ru.intelligency.scholarship.data.portfolio

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import ru.intelligency.scholarship.data.extensions.toCreateRequestModel
import ru.intelligency.scholarship.data.extensions.toDomainModel
import ru.intelligency.scholarship.data.extensions.toEntity
import ru.intelligency.scholarship.data.extensions.toRequestModel
import ru.intelligency.scholarship.data.myapplications.ApplicationDocumentCrossRefDao
import ru.intelligency.scholarship.data.profile.UserSharedPreferences
import ru.intelligency.scholarship.domain.portfolio.DocumentsRepository
import ru.intelligency.scholarship.domain.portfolio.model.Document

class DocumentsRepositoryImpl(
    private val documentDao: DocumentDao,
    private val applicationDocumentCrossRefDao: ApplicationDocumentCrossRefDao,
    private val documentsApi: DocumentsApi,
    private val userSharedPreferences: UserSharedPreferences,
    private val imageProvider: ImageProvider
) : DocumentsRepository {

    override fun getAllDocuments(): Flow<List<Document>> =
        documentDao.getAllDocuments()
            .map { list ->
                list.map { docEntity -> docEntity.toDomainModel() }
            }

    override fun getDocument(documentId: Int): Flow<Document?> {
        return documentDao.getDocumentById(documentId).map { it?.toDomainModel() }
    }

    override fun getDefaultEventTypes(): List<String> {
        return defaultEventTypes
    }

    override fun getDefaultEventStatuses(): List<String> {
        return defaultEventStatuses
    }

    override suspend fun createDocument(document: Document) {
        documentDao.insertDocument(document.toEntity())
        val fullName = userSharedPreferences.getUserData().fullName
        val file = imageProvider.getImageFile(document.fileName)
        val requestFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("img", file.name, requestFile)
        documentsApi.postDocument(document.toCreateRequestModel(fullName))
    }

    override suspend fun updateDocument(document: Document) {
        documentDao.updateDocument(document.toEntity())
        val fullName = userSharedPreferences.getUserData().fullName
        documentsApi.putDocument(document.id, document.toRequestModel(fullName))
    }

    override suspend fun deleteDocument(documentId: Int) {
        documentDao.deleteDocument(documentId)
        applicationDocumentCrossRefDao.deleteDocumentWithId(documentId)
        documentsApi.deleteDocument(documentId)
    }

    companion object {

        private val defaultEventTypes = listOf(
            "Хакатон",
            "Олимпиада",
            "Конференция",
            "Конкурс",
            "Соревнование",
            "Состязание",
            "Другое"
        )
        private val defaultEventStatuses =
            listOf("Международное", "Всероссийское", "Ведомственное", "Региональное", "Другое")
    }
}
