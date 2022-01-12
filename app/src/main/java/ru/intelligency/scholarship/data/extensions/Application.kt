package ru.intelligency.scholarship.data.extensions

import ru.intelligency.scholarship.data.myapplications.ApplicationEntity
import ru.intelligency.scholarship.domain.myapplications.models.Application

fun Application.toEntity(): ApplicationEntity {
    return ApplicationEntity(
        applicationId = id,
        applicationStatus = applicationStatus,
        scholarshipType = scholarshipType,
        fullName = fullName,
        academicGroupNumber = academicGroupNumber,
        specialityCode = specialityCode,
        specialityName = specialityName,
        totalMarksCount = totalMarksCount,
        excellentMarksCount = excellentMarksCount,
        sendingDate = sendingDate
    )
}
