package ru.intelligency.scholarship.data.myapplications

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ApplicationDocumentCrossRefDao {

    @Insert
    suspend fun insertApplicationDocumentCrossRef(applicationDocumentCrossRef: ApplicationDocumentCrossRef)

    @Delete
    suspend fun deleteApplicationDocumentCrossRef(applicationDocumentCrossRef: ApplicationDocumentCrossRef)

    @Query("DELETE FROM applicationdocumentcrossref WHERE applicationId == :applicationId")
    suspend fun deleteApplicationWithId(applicationId: Int)

    @Query("DELETE FROM applicationdocumentcrossref WHERE documentId == :documentId")
    suspend fun deleteDocumentWithId(documentId: Int)
}
