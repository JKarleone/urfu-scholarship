package ru.intelligency.scholarship.presentation.di

import dagger.Module
import dagger.Provides
import ru.intelligency.scholarship.data.myapplications.ApplicationsRepositoryImpl
import ru.intelligency.scholarship.data.portfolio.DocumentsRepositoryImpl
import ru.intelligency.scholarship.data.profile.ProfileRepositoryImpl
import ru.intelligency.scholarship.domain.myapplications.ApplicationsRepository
import ru.intelligency.scholarship.domain.portfolio.DocumentsRepository
import ru.intelligency.scholarship.domain.profile.ProfileRepository

@Module
class RepositoryModule {

    @Provides
    fun provideDocumentsRepository(): DocumentsRepository {
        return DocumentsRepositoryImpl()
    }

    @Provides
    fun provideApplicationsRepository(): ApplicationsRepository {
        return ApplicationsRepositoryImpl()
    }

    @Provides
    fun provideProfileRepository(): ProfileRepository {
        return ProfileRepositoryImpl()
    }
}
