package ru.intelligency.scholarship.data

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.intelligency.scholarship.data.myapplications.ApplicationDao
import ru.intelligency.scholarship.data.myapplications.ApplicationDocumentCrossRef
import ru.intelligency.scholarship.data.myapplications.ApplicationDocumentCrossRefDao
import ru.intelligency.scholarship.data.myapplications.ApplicationEntity
import ru.intelligency.scholarship.data.portfolio.DocumentDao
import ru.intelligency.scholarship.data.portfolio.DocumentEntity

@Database(
    entities = [ApplicationEntity::class, DocumentEntity::class, ApplicationDocumentCrossRef::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun applicationDao(): ApplicationDao
    abstract fun documentDao(): DocumentDao
    abstract fun applicationDocumentCrossRefDao(): ApplicationDocumentCrossRefDao
}
