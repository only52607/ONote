package com.ooooonly.onote.di

import android.content.ContentResolver
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NoteStoreDirectory

@InstallIn(SingletonComponent::class)
@Module
class AppFileModule {
    @Provides
    @Singleton
    @NoteStoreDirectory
    fun provideNoteStoreDirectoryFile(
        @ApplicationContext appContext: Context
    ): File {
        return File(appContext.filesDir, "notes").also { if(!it.exists()) it.mkdir() }
    }
}