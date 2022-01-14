package ru.intelligency.scholarship.data.myapplications

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ApplicationDao {

    @Query("SELECT * FROM applications")
    fun getAllApplications(): Flow<List<ApplicationEntity>>

    @Query("SELECT * FROM applications WHERE applicationId == :applicationId")
    fun getApplicationById(applicationId: Int): Flow<ApplicationWithDocumentsEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createApplication(application: ApplicationEntity): Long

    @Update
    suspend fun updateApplication(applicationEntity: ApplicationEntity)

    @Query("DELETE FROM applications WHERE applicationId == :applicationId")
    suspend fun deleteApplication(applicationId: Int)
}
