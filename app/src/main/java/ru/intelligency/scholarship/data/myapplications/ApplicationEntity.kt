package ru.intelligency.scholarship.data.myapplications

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "applications")
data class ApplicationEntity(
    @PrimaryKey(autoGenerate = true)
    val applicationId: String = "",
    @ColumnInfo(name = "type")
    val type: String,
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
    val excellentMarksCount: Int
)
