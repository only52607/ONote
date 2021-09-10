package com.ooooonly.onote.data.dao

import androidx.room.*
import com.ooooonly.onote.model.entity.NotePackage

@Dao
interface NotePackageDao {
    @Query("SELECT * FROM note_package")
    suspend fun loadAllNotePackages(): Array<NotePackage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNotePackage(notePackage: NotePackage): Long

    @Delete
    suspend fun deleteNotePackage(notePackage: NotePackage)
}