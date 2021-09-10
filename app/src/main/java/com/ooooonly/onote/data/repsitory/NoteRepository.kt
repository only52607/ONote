package com.ooooonly.onote.data.repsitory

import com.ooooonly.onote.model.entity.Note
import com.ooooonly.onote.model.entity.NotePackage

interface NoteRepository {
    suspend fun listPackages(): Array<NotePackage>

    suspend fun removePackage(notePackage: NotePackage)

    suspend fun savePackage(notePackage: NotePackage)

    suspend fun listNotesByPackage(notePackage: NotePackage): Array<Note>

    suspend fun saveNote(note: Note)

    suspend fun removeNote(note: Note)
}