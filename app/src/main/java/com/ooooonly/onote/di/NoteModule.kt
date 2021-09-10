package com.ooooonly.onote.di

import com.ooooonly.onote.data.repsitory.NoteRepository
import com.ooooonly.onote.data.repsitory.impl.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NoteViewModelModule {
    @Binds
    abstract fun bindNoteRepository(
        noteRepositoryImpl: NoteRepositoryImpl
    ): NoteRepository
}