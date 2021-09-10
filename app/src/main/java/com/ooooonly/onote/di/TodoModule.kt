package com.ooooonly.onote.di

import com.ooooonly.onote.data.repsitory.TodoRepository
import com.ooooonly.onote.data.repsitory.impl.TodoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class TodoViewModelModule {
    @Binds
    abstract fun bindTodoRepository(
        todoRepositoryImpl: TodoRepositoryImpl
    ): TodoRepository
}