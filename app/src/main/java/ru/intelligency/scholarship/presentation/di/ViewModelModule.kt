package ru.intelligency.scholarship.presentation.di

import dagger.Module
import dagger.Provides
import ru.intelligency.scholarship.domain.myapplications.ApplicationsInteractor
import ru.intelligency.scholarship.domain.portfolio.PortfolioInteractor
import ru.intelligency.scholarship.domain.profile.ProfileInteractor
import ru.intelligency.scholarship.presentation.ui.myapplications.viewmodels.ApplicationsViewModelFactory
import ru.intelligency.scholarship.presentation.ui.portfolio.fragments.NewDocumentViewModelFactory
import ru.intelligency.scholarship.presentation.ui.portfolio.viewmodels.PortfolioViewModelFactory
import ru.intelligency.scholarship.presentation.ui.profile.ProfileViewModelFactory

@Module
class ViewModelModule {

    @Provides
    fun providePortfolioViewModelFactory(interactor: PortfolioInteractor): PortfolioViewModelFactory {
        return PortfolioViewModelFactory(interactor)
    }

    @Provides
    fun provideNewDocumentViewModelFactory(interactor: PortfolioInteractor): NewDocumentViewModelFactory {
        return NewDocumentViewModelFactory(interactor)
    }

    @Provides
    fun provideApplicationsViewModelFactory(interactor: ApplicationsInteractor): ApplicationsViewModelFactory {
        return ApplicationsViewModelFactory(interactor)
    }

    @Provides
    fun provideProfileViewModelFactory(interactor: ProfileInteractor): ProfileViewModelFactory {
        return ProfileViewModelFactory(interactor)
    }
}
