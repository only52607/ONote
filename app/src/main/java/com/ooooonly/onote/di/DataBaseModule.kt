package com.ooooonly.onote.di

import android.content.Context
import androidx.room.Room
import com.ooooonly.onote.data.AppDatabase
import com.ooooonly.onote.data.dao.NoteDao
import com.ooooonly.onote.data.dao.NotePackageDao
import com.ooooonly.onote.data.dao.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataBaseModule {
    @Provides
    fun provideNoteDao(appDatabase: AppDatabase): NoteDao {
        return appDatabase.noteDao()
    }

    @Provides
    fun provideNotePackageDao(appDatabase: AppDatabase): NotePackageDao {
        return appDatabase.notePackageDao()
    }

    @Provides
    fun provideTodoDao(appDatabase: AppDatabase): TodoDao {
        return appDatabase.todoDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext appContext: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            AppDatabase.NAME
        ).fallbackToDestructiveMigration().build() // TODO: implement migrations
    }
}