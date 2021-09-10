package com.ooooonly.onote.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ooooonly.onote.data.dao.NoteDao
import com.ooooonly.onote.data.dao.NotePackageDao
import com.ooooonly.onote.data.dao.TodoDao
import com.ooooonly.onote.model.converter.DateConverter
import com.ooooonly.onote.model.converter.FileConverter
import com.ooooonly.onote.model.converter.NotePackageTypeConverter
import com.ooooonly.onote.model.converter.NoteTypeConverter
import com.ooooonly.onote.model.entity.Note
import com.ooooonly.onote.model.entity.NotePackage
import com.ooooonly.onote.model.entity.Todo

@Database(
    entities = [Note::class, Todo::class, NotePackage::class],
    version = 1
)
@TypeConverters(
    FileConverter::class,
    DateConverter::class,
    NoteTypeConverter::class,
    NotePackageTypeConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun todoDao(): TodoDao
    abstract fun notePackageDao(): NotePackageDao

    companion object {
        const val NAME = "main_database"
    }
}