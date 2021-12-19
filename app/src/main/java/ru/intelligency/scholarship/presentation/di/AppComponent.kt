package ru.intelligency.scholarship.presentation.di

import dagger.Component
import ru.intelligency.scholarship.presentation.ui.portfolio.DocumentDetailsFragment
import ru.intelligency.scholarship.presentation.ui.portfolio.PortfolioFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        RepositoryModule::class,
        InteractorModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(portfolioFragment: PortfolioFragment)
    fun inject(documentDetailsFragment: DocumentDetailsFragment)
}
