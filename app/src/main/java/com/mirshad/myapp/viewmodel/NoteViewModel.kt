package com.mirshad.myapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mirshad.myapp.data.model.Note
import com.mirshad.myapp.data.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val noteRepository: NoteRepository = NoteRepository(application)
    private val allNotes: LiveData<List<Note>> = noteRepository.getAllNotes()

    fun insert(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.insert(note)
        }
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }

    fun getNoteById(id: Long): LiveData<Note?> {
        return noteRepository.getNoteById(id)
    }

    fun update(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.update(note)
        }
    }

    fun deleteNoteById(noteId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteNoteById(noteId)
        }
    }


}
