package ru.intelligency.scholarship.domain.myapplications.models

import ru.intelligency.scholarship.presentation.utils.Status

data class Application(
    val id: Int = 0,
    val scholarshipType: String,
    val fullName: String,
    val academicGroupNumber: String,
    val specialityCode: String,
    val specialityName: String,
    val totalMarksCount: Int,
    val excellentMarksCount: Int,
    val applicationStatus: Status,
    val sendingDate: Long
)
