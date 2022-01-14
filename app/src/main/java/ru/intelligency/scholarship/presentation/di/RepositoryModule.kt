package ru.intelligency.scholarship.presentation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.intelligency.scholarship.data.myapplications.ApplicationDao
import ru.intelligency.scholarship.data.myapplications.ApplicationDocumentCrossRefDao
import ru.intelligency.scholarship.data.myapplications.ApplicationsRepositoryImpl
import ru.intelligency.scholarship.data.portfolio.DocumentDao
import ru.intelligency.scholarship.data.portfolio.DocumentsApi
import ru.intelligency.scholarship.data.portfolio.DocumentsRepositoryImpl
import ru.intelligency.scholarship.data.portfolio.ImageProvider
import ru.intelligency.scholarship.data.profile.ProfileRepositoryImpl
import ru.intelligency.scholarship.data.profile.UserSharedPreferences
import ru.intelligency.scholarship.domain.myapplications.ApplicationsRepository
import ru.intelligency.scholarship.domain.portfolio.DocumentsRepository
import ru.intelligency.scholarship.domain.profile.ProfileRepository
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideDocumentsRepository(
        documentDao: DocumentDao,
        applicationWithDocumentsRefDao: ApplicationDocumentCrossRefDao,
        documentsApi: DocumentsApi,
        userSharedPreferences: UserSharedPreferences,
        imageProvider: ImageProvider
    ): DocumentsRepository {
        return DocumentsRepositoryImpl(
            documentDao,
            applicationWithDocumentsRefDao,
            documentsApi,
            userSharedPreferences,
            imageProvider
        )
    }

    @Provides
    @Singleton
    fun provideApplicationsRepository(
        applicationDao: ApplicationDao,
        applicationWithDocumentsRefDao: ApplicationDocumentCrossRefDao
    ): ApplicationsRepository {
        return ApplicationsRepositoryImpl(applicationDao, applicationWithDocumentsRefDao)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(userSharedPreferences: UserSharedPreferences): ProfileRepository {
        return ProfileRepositoryImpl(userSharedPreferences)
    }

    @Provides
    @Singleton
    fun provideImageProvider(context: Context): ImageProvider {
        return ImageProvider(context)
    }
}
