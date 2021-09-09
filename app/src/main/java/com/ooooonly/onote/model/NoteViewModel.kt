package com.ooooonly.onote.model

import androidx.lifecycle.ViewModel
import com.ooooonly.onote.data.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    noteRepository: NoteRepository
): ViewModel() {

}