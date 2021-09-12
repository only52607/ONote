package com.ooooonly.onote.data.repsitory.impl

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.ooooonly.onote.data.dao.NoteDao
import com.ooooonly.onote.data.dao.NotePackageDao
import com.ooooonly.onote.data.repsitory.NoteRepository
import com.ooooonly.onote.model.entity.Note
import com.ooooonly.onote.model.entity.NotePackage
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val notePackageDao: NotePackageDao
): NoteRepository {
    override suspend fun listPackages(): Array<NotePackage> {
        return notePackageDao.loadAllNotePackages()
    }

    override suspend fun removePackage(notePackage: NotePackage) {
        val notes = listNotesByPackage(notePackage)
        noteDao.deleteNote(*notes)
        return notePackageDao.deleteNotePackage(notePackage)
    }

    override suspend fun savePackage(notePackage: NotePackage) {
        notePackage.updateModifyTime()
        notePackage.id = notePackageDao.saveNotePackage(notePackage)
    }

    override suspend fun listNotesByPackage(notePackage: NotePackage): Array<Note> {
        if (notePackage.isAll) {
            return noteDao.loadAllNotes()
        }
        return notePackage.id?.let {
            noteDao.loadNotesByPackage(it)
        } ?: arrayOf()
    }

    override suspend fun saveNote(note: Note) {
        note.updateModifyTime()
        note.id = noteDao.saveNote(note)
    }

    override suspend fun removeNote(note: Note) {
        return noteDao.deleteNote(note)
    }
}