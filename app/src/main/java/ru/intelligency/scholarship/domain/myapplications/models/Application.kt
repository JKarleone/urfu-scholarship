package ru.intelligency.scholarship.domain.myapplications.models

import ru.intelligency.scholarship.domain.portfolio.model.SimpleDate
import ru.intelligency.scholarship.presentation.utils.Status
import java.util.*

data class Application(
    val id: String = UUID.randomUUID().toString(),
    val scholarshipType: String,
    val fullName: String,
    val academicGroupNumber: String,
    val specialityCode: String,
    val specialityName: String,
    val totalMarksCount: Int,
    val excellentMarksCount: Int,
    val applicationStatus: Status,
    val sendingDate: SimpleDate
)
