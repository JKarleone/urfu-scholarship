package ru.intelligency.scholarship.data.myapplications

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import ru.intelligency.scholarship.data.portfolio.DocumentEntity

data class ApplicationWithDocumentsEntity(
    @Embedded val application: ApplicationEntity,
    @Relation(
        parentColumn = "applicationId",
        entityColumn = "documentId",
        associateBy = Junction(ApplicationDocumentCrossRef::class)
    )
    val documents: List<DocumentEntity>
)
