package com.mirshad.myapp.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.mirshad.myapp.data.dao.NoteDao
import com.mirshad.myapp.data.database.AppDatabase
import com.mirshad.myapp.data.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository(context: Context) {
    private val noteDao: NoteDao = AppDatabase.getInstance(context).noteDao()

    suspend fun insert(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.insert(note)
        }
    }

    suspend fun update(note: Note) {
        withContext(Dispatchers.IO) {
            noteDao.update(note)
        }
    }

    suspend fun deleteById(noteId: Long) {
        withContext(Dispatchers.IO) {
            noteDao.deleteById(noteId)
        }
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return noteDao.getAllNotes()
    }

    fun getNoteById(id: Long): LiveData<Note?> {
        return noteDao.getNoteById(id)
    }

    suspend fun deleteNoteById(noteId: Long) {
        withContext(Dispatchers.IO) {
            noteDao.deleteById(noteId)
        }
    }

}
