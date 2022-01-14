package ru.intelligency.scholarship.domain.profile

import kotlinx.coroutines.flow.Flow
import ru.intelligency.scholarship.domain.profile.models.Profile

class ProfileInteractor(
    private val profileRepository: ProfileRepository
) {

    fun getProfileFlow(): Flow<Profile> {
        return profileRepository.getProfile()
    }

    fun updateProfile(profile: Profile) {
        profileRepository.updateProfile(profile)
    }
}
