package com.ooooonly.onote.data.dao

import androidx.room.*
import com.ooooonly.onote.model.entity.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM note order by modify_time desc")
    suspend fun loadAllNotes(): Array<Note>

    @Query("SELECT * FROM note where package_id = :packageId order by modify_time desc")
    suspend fun loadNotesByPackage(packageId: Long): Array<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(note: Note): Long

    @Delete
    suspend fun deleteNote(vararg notes: Note)
}