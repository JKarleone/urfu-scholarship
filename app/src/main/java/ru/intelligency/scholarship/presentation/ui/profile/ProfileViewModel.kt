package ru.intelligency.scholarship.presentation.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import ru.intelligency.scholarship.domain.profile.ProfileInteractor
import ru.intelligency.scholarship.domain.profile.models.Profile

class ProfileViewModel(
    private val profileInteractor: ProfileInteractor
) : ViewModel() {


    suspend fun getProfile(): StateFlow<Profile> {
        return profileInteractor.getProfile()
            .stateIn(viewModelScope)
    }
}
