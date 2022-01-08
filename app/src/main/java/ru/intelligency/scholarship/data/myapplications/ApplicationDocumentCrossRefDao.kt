package ru.intelligency.scholarship.data.myapplications

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

@Dao
interface ApplicationDocumentCrossRefDao {

    @Insert
    suspend fun insertApplicationDocumentCrossRef(applicationDocumentCrossRef: ApplicationDocumentCrossRef)

    @Delete
    suspend fun deleteApplicationDocumentCrossRef(applicationDocumentCrossRef: ApplicationDocumentCrossRef)
}
