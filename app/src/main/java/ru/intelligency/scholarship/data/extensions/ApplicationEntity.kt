package ru.intelligency.scholarship.data.extensions

import ru.intelligency.scholarship.data.myapplications.ApplicationEntity
import ru.intelligency.scholarship.domain.myapplications.models.Application

fun ApplicationEntity.toDomainModel(): Application {
    return Application(
        id = applicationId,
        scholarshipType = scholarshipType,
        fullName = fullName,
        academicGroupNumber = academicGroupNumber,
        specialityCode = specialityCode,
        specialityName = specialityName,
        totalMarksCount = totalMarksCount,
        excellentMarksCount = excellentMarksCount,
        applicationStatus = applicationStatus,
        sendingDate = sendingDate
    )
}
