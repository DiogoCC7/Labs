package com.example.labs.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.labs.data.db.NoteDatabase
import com.example.labs.data.entities.Note
import com.example.labs.data.repositories.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val readAllNotes: LiveData<List<Note>>;
    private val repository: NoteRepository;

    init {
        val nodeDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(nodeDao)

        readAllNotes = repository.readAllNotes;
    }

    fun getNotes(): LiveData<List<Note>> {
        return readAllNotes;
    }

    fun  addNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note)
        }
    }

    fun  deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }
}