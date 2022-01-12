package ru.intelligency.scholarship.presentation.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.intelligency.scholarship.data.AppDatabase
import ru.intelligency.scholarship.data.myapplications.ApplicationDao
import ru.intelligency.scholarship.data.myapplications.ApplicationDocumentCrossRefDao
import ru.intelligency.scholarship.data.portfolio.DocumentDao
import ru.intelligency.scholarship.data.profile.UserSharedPreferences

@Module
class DataModule {

    @Provides
    fun provideRoomDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "scholarship_urfu"
        ).build()
    }

    @Provides
    fun provideApplicationDao(db: AppDatabase): ApplicationDao {
        return db.applicationDao()
    }

    @Provides
    fun provideDocumentDao(db: AppDatabase): DocumentDao {
        return db.documentDao()
    }

    @Provides
    fun provideApplicationDocumentCrossRefDao(db: AppDatabase): ApplicationDocumentCrossRefDao {
        return db.applicationDocumentCrossRefDao()
    }

    @Provides
    fun provideUserSharedPreferences(context: Context): UserSharedPreferences {
        return UserSharedPreferences(context)
    }
}
