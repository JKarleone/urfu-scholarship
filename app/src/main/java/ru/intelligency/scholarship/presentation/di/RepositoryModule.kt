package ru.intelligency.scholarship.presentation.di

import dagger.Module
import dagger.Provides
import ru.intelligency.scholarship.data.portfolio.DocumentsRepositoryImpl
import ru.intelligency.scholarship.domain.portfolio.DocumentsRepository

@Module
class RepositoryModule {

    @Provides
    fun provideDocumentsRepository(): DocumentsRepository {
        return DocumentsRepositoryImpl()
    }
}
