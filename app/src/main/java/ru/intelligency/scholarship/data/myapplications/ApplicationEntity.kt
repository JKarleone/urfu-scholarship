package ru.intelligency.scholarship.data.myapplications

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.intelligency.scholarship.presentation.utils.Status

@Entity(tableName = "applications")
data class ApplicationEntity(
    @PrimaryKey
    val applicationId: Int = 0,
    @ColumnInfo(name = "application_status")
    val applicationStatus: Status,
    @ColumnInfo(name = "scholarship_type")
    val scholarshipType: String,
    @ColumnInfo(name = "full_name")
    val fullName: String,
    @ColumnInfo(name = "academic_group_number")
    val academicGroupNumber: String,
    @ColumnInfo(name = "speciality_code")
    val specialityCode: String,
    @ColumnInfo(name = "speciality_name")
    val specialityName: String,
    @ColumnInfo(name = "total_marks_count")
    val totalMarksCount: Int,
    @ColumnInfo(name = "excellent_marks_count")
    val excellentMarksCount: Int,
    @ColumnInfo(name = "sending_date")
    val sendingDate: Long
)
