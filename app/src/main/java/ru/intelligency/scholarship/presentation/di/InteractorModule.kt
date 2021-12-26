package ru.intelligency.scholarship.presentation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.intelligency.scholarship.domain.myapplications.ApplicationsInteractor
import ru.intelligency.scholarship.domain.myapplications.ApplicationsRepository
import ru.intelligency.scholarship.domain.portfolio.DocumentsRepository
import ru.intelligency.scholarship.domain.portfolio.PortfolioInteractor
import ru.intelligency.scholarship.domain.profile.ProfileInteractor
import ru.intelligency.scholarship.domain.profile.ProfileRepository

@Module
class InteractorModule {

    @Provides
    fun providePortfolioInteractor(
        context: Context,
        documentsRepository: DocumentsRepository
    ): PortfolioInteractor {
        return PortfolioInteractor(documentsRepository, context)
    }

    @Provides
    fun provideApplicationsInteractor(
        applicationsRepository: ApplicationsRepository
    ): ApplicationsInteractor {
        return ApplicationsInteractor(applicationsRepository)
    }

    @Provides
    fun provideProfileInteractor(
        profileRepository: ProfileRepository
    ): ProfileInteractor {
        return ProfileInteractor(profileRepository)
    }
}
