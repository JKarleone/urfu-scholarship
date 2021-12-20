package ru.intelligency.scholarship.presentation.di

import dagger.Module
import dagger.Provides
import ru.intelligency.scholarship.domain.portfolio.PortfolioInteractor
import ru.intelligency.scholarship.presentation.ui.portfolio.viewmodels.PortfolioViewModelFactory

@Module
class ViewModelModule {

    @Provides
    fun providePortfolioViewModelFactory(interactor: PortfolioInteractor): PortfolioViewModelFactory {
        return PortfolioViewModelFactory(interactor)
    }
}
