package ru.intelligency.scholarship.presentation.di

import dagger.Component
import ru.intelligency.scholarship.presentation.ui.myapplications.fragments.ApplicationDetailsFragment
import ru.intelligency.scholarship.presentation.ui.myapplications.fragments.MyApplicationsFragment
import ru.intelligency.scholarship.presentation.ui.portfolio.fragments.DocumentDetailsEditFragment
import ru.intelligency.scholarship.presentation.ui.portfolio.fragments.DocumentDetailsFragment
import ru.intelligency.scholarship.presentation.ui.portfolio.fragments.PortfolioFragment
import ru.intelligency.scholarship.presentation.ui.portfolio.fragments.ScanDocumentInfoFragment
import ru.intelligency.scholarship.presentation.ui.profile.ProfileEditFragment
import ru.intelligency.scholarship.presentation.ui.profile.ProfileFragment
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
    fun inject(documentDetailsEditFragment: DocumentDetailsEditFragment)
    fun inject(scanDocumentInfoFragment: ScanDocumentInfoFragment)
    fun inject(myApplicationsFragment: MyApplicationsFragment)
    fun inject(applicationDetailsFragment: ApplicationDetailsFragment)
    fun inject(profileFragment: ProfileFragment)
    fun inject(profileEditFragment: ProfileEditFragment)
}
