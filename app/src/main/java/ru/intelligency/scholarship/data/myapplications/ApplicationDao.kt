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
    fun getAllApplicationsFlow(): Flow<List<ApplicationEntity>>

    @Query("SELECT * FROM applications")
    suspend fun getAllApplications(): List<ApplicationEntity>

    @Query("SELECT * FROM applications WHERE applicationId == :applicationId")
    fun getApplicationFlowById(applicationId: Int): Flow<ApplicationWithDocumentsEntity?>

    @Query("SELECT * FROM applications WHERE applicationId == :applicationId")
    suspend fun getApplicationById(applicationId: Int): ApplicationEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createApplication(application: ApplicationEntity): Long

    @Update
    suspend fun updateApplication(applicationEntity: ApplicationEntity)

    @Query("DELETE FROM applications WHERE applicationId == :applicationId")
    suspend fun deleteApplication(applicationId: Int)
}
