package ru.intelligency.scholarship.domain.profile

import kotlinx.coroutines.flow.Flow
import ru.intelligency.scholarship.domain.profile.models.Profile

interface ProfileRepository {

    fun getProfile(): Flow<Profile>
}
