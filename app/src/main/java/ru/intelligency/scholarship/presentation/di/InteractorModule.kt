package ru.intelligency.scholarship.presentation.di

import dagger.Module
import dagger.Provides
import ru.intelligency.scholarship.domain.portfolio.DocumentsRepository
import ru.intelligency.scholarship.domain.portfolio.PortfolioInteractor

@Module
class InteractorModule {

    @Provides
    fun providePortfolioInteractor(documentsRepository: DocumentsRepository): PortfolioInteractor {
        return PortfolioInteractor(documentsRepository)
    }
}
