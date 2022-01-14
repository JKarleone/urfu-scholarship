package ru.intelligency.scholarship.domain.profile.models

data class Profile(
    val id: Int,
    val fullName: String,
    val academicGroupNumber: String,
    val email: String,
    val phoneNumber: String
)
