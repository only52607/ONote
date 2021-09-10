package com.ooooonly.onote.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ooooonly.onote.data.repsitory.NoteRepository
import com.ooooonly.onote.di.NoteStoreDirectory
import com.ooooonly.onote.model.entity.Note
import com.ooooonly.onote.model.entity.NotePackage
import com.ooooonly.onote.model.entity.NotePackageType
import com.ooooonly.onote.model.entity.NoteType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    @NoteStoreDirectory
    private val noteStoreDirectory: File
) : ViewModel() {
    private val allNotePackageState = NotePackageState(NotePackage.ALL, this)

    private val _notes = mutableStateListOf<NoteState>()
    val notes: List<NoteState> = _notes

    private val _notePackages = mutableStateListOf(allNotePackageState)
    val notePackage: List<NotePackageState> = _notePackages

    private val _currentNotePackage: MutableState<NotePackageState> = mutableStateOf(allNotePackageState)
    val currentNotePackage by _currentNotePackage

    private val _editingNoteState = mutableStateOf<NoteState?>(null)
    val editingNoteState by _editingNoteState

    init {
        viewModelScope.launch { loadNotePackages() }
        viewModelScope.launch { updateNotes() }
    }

    fun setCurrentNotePackage(notePackageState: NotePackageState) {
        _currentNotePackage.value = notePackageState
        viewModelScope.launch {
            updateNotes()
        }
    }

    fun removeNotePackage(notePackageState: NotePackageState) {
        _notePackages.remove(notePackageState)
        _notePackages.add(allNotePackageState)
        viewModelScope.launch {
            noteRepository.removePackage(notePackageState.entity)
        }
    }

    fun removeNote(noteState: NoteState) {
        _notes.remove(noteState)
        viewModelScope.launch {
            noteRepository.removeNote(noteState.entity)
        }
    }

    fun setEditingNoteState(noteState: NoteState) {
        _editingNoteState.value = noteState
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNewEditingState(noteState: NoteState, packageState: NotePackageState = allNotePackageState) {
        _editingNoteState.value = NoteState(
            entity = Note(file = File(noteStoreDirectory, System.currentTimeMillis().toString())),
            notePackageState = packageState,
            noteViewModel = this
        )
    }

    fun clearEditingState() {
        _editingNoteState.value = null
    }

    private suspend fun loadNotePackages() {
        _notePackages.clear()
        _notePackages.addAll(
            noteRepository.listPackages().map { NotePackageState(it, this) }
        )
    }

    private suspend fun updateNotes() {
        _notes.clear()
        _notes.addAll(
            noteRepository.listNotesByPackage(_currentNotePackage.value.entity).map { noteEntity ->
                NoteState(noteEntity, _currentNotePackage.value, this)
            }
        )
    }

    internal fun saveNoteState(noteState: NoteState) {
        viewModelScope.launch {
            noteRepository.saveNote(noteState.entity)
            updateNotes()
        }
    }

    internal fun saveNotePackageState(notePackageState: NotePackageState) {
        viewModelScope.launch {
            noteRepository.savePackage(notePackageState.entity)
            loadNotePackages()
        }
    }
}

class NoteState(
    override val entity: Note,
    notePackageState: NotePackageState,
    private val noteViewModel: NoteViewModel
) : RoomEntityState<Note> {
    var title: String? by mutableStateOf(entity.title)
    var brief: String? by mutableStateOf(entity.brief)
    var type: NoteType by mutableStateOf(entity.type)
    var packageState: NotePackageState by mutableStateOf(notePackageState)
    var background: Int? by mutableStateOf(entity.background)

    init {
        noteViewModel.viewModelScope.launch {
            snapshotFlow { title }.collect { entity.title = it }
        }
        noteViewModel.viewModelScope.launch {
            snapshotFlow { brief }.collect { entity.brief = it }
        }
        noteViewModel.viewModelScope.launch {
            snapshotFlow { type }.collect { entity.type = it }
        }
        noteViewModel.viewModelScope.launch {
            snapshotFlow { packageState }.collect { entity.packageId = it.entity.id!! }
        }
        noteViewModel.viewModelScope.launch {
            snapshotFlow { background }.collect { entity.background = it }
        }
    }

    override suspend fun save() {
        noteViewModel.saveNoteState(this)
    }
}

class NotePackageState(
    override val entity: NotePackage,
    private val noteViewModel: NoteViewModel
) : RoomEntityState<NotePackage> {
    var name: String by mutableStateOf(entity.name)
    var type: NotePackageType by mutableStateOf(entity.type)
    var password: String? by mutableStateOf(entity.password)
    var cover: Int? by mutableStateOf(entity.cover)

    init {
        noteViewModel.viewModelScope.launch {
            snapshotFlow { name }.collect { entity.name = it }
        }
        noteViewModel.viewModelScope.launch {
            snapshotFlow { type }.collect { entity.type = it }
        }
        noteViewModel.viewModelScope.launch {
            snapshotFlow { password }.collect { entity.password = it }
        }
        noteViewModel.viewModelScope.launch {
            snapshotFlow { cover }.collect { entity.cover = it }
        }
    }

    override suspend fun save() {
        noteViewModel.saveNotePackageState(this)
    }
}