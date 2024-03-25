package com.example.labs.data.repositories

import androidx.lifecycle.LiveData
import com.example.labs.data.daos.NoteDao
import com.example.labs.data.entities.Note
import java.time.LocalDateTime

class NotesRepository(private val noteDao: NoteDao) {
    val readAllNotes: LiveData<List<Note>> = noteDao.readAllNotes()

    suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }

    suspend fun updateNote(note: Note) {
        note.updatedAt = LocalDateTime.now()
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note) {
        note.deletedAt = LocalDateTime.now()
        noteDao.deleteNote(note)
    }
}