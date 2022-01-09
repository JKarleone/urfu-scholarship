package ru.intelligency.scholarship.data.profile

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.intelligency.scholarship.domain.profile.ProfileRepository
import ru.intelligency.scholarship.domain.profile.models.Profile

class ProfileRepositoryImpl(
    private val sharedPreferences: UserSharedPreferences
) : ProfileRepository {

    override fun getProfile(): Flow<Profile> {
        return flow {
            emit(sharedPreferences.getUserData())
        }
    }

    override fun updateProfile(profile: Profile) {
        sharedPreferences.updateUserData(profile)
    }
}
