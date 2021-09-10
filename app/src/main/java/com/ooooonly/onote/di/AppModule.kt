package com.ooooonly.onote.di

import dagger.Module
import dagger.hilt.InstallIn
import javax.inject.Singleton
import android.content.ContentResolver
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    @Singleton
    fun provideSharePreferences(
        @ApplicationContext appContext: Context
    ): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(appContext)
    }

    @Provides
    @Singleton
    fun provideContentResolver(
        @ApplicationContext appContext: Context
    ): ContentResolver {
        return appContext.contentResolver
    }
}