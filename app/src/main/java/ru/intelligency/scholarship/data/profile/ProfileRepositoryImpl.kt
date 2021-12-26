package ru.intelligency.scholarship.data.profile

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.intelligency.scholarship.domain.profile.ProfileRepository
import ru.intelligency.scholarship.domain.profile.models.Profile

class ProfileRepositoryImpl : ProfileRepository {

    private val profile = Profile(
        id = 0,
        fullName = "Глухов Антон Сергеевич",
        academicGroupNumber = "РИ-480022",
        email = "enton@nezavod.ru",
        phoneNumber = "+7 (952) 726-66-470"
    )

    override fun getProfile(): Flow<Profile> {
        return flow {
            emit(profile)
        }
    }
}
