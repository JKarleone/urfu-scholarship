package ru.intelligency.scholarship.data.myapplications

import androidx.room.Entity

@Entity(primaryKeys = ["applicationId", "documentId"])
data class ApplicationDocumentCrossRef(
    val applicationId: String,
    val documentId: String
)
