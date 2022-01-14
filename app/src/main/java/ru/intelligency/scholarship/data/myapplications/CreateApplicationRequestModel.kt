package ru.intelligency.scholarship.data.myapplications

import com.google.gson.annotations.SerializedName

data class CreateApplicationRequestModel(
    @SerializedName("status")
    val status: Int,
    @SerializedName("fullname")
    val fullName: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("academic_group_number")
    val academicGroupNumber: String,
    @SerializedName("speciality_code")
    val specialityCode: String,
    @SerializedName("speciality_name")
    val specialityName: String,
    @SerializedName("total_marks_count")
    val totalMarksCount: Int,
    @SerializedName("excellent_marks_count")
    val excellentMarksCount: Int,
    @SerializedName("documents_id")
    val documentIds: List<Int>,
    @SerializedName("user_id")
    val userId: Int
)
