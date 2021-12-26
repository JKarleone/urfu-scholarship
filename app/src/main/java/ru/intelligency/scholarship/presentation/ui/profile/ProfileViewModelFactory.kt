package ru.intelligency.scholarship.presentation.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.intelligency.scholarship.domain.profile.ProfileInteractor

class ProfileViewModelFactory(
    private val profileInteractor: ProfileInteractor
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(profileInteractor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
