package ru.intelligency.scholarship.data.portfolio

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DocumentDao {

    @Query("SELECT * FROM documents")
    fun getAllDocumentsFlow(): Flow<List<DocumentEntity>>

    @Query("SELECT * FROM documents")
    suspend fun getAllDocuments(): List<DocumentEntity>

    @Query("SELECT * FROM documents WHERE documentId == :documentId")
    fun getDocumentFlowById(documentId: Int): Flow<DocumentEntity?>

    @Query("SELECT * FROM documents WHERE documentId == :documentId")
    suspend fun getDocumentById(documentId: Int): DocumentEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDocument(document: DocumentEntity): Long

    @Update
    suspend fun updateDocument(document: DocumentEntity)

    @Query("DELETE FROM documents WHERE documentId == :documentId")
    suspend fun deleteDocument(documentId: Int)
}
