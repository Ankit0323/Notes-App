
package com.example.notesapp


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application){
    private var repository:NotesRepository
    var allNotes : LiveData<List<Notes>>
    init{
        var dao=NotesDatabase.getDatabase(application).getNotesDao()
         repository=NotesRepository(dao)
        allNotes=repository.getAllNotes
    }
    fun insertNote(note:Notes)=viewModelScope.launch(Dispatchers.IO ) {
        repository.insert(note)
    }
    fun deleteNote(note: Notes)=viewModelScope.launch(Dispatchers.IO ) {
        repository.delete(note)
    }
    fun updateNote(note:Notes)=viewModelScope.launch(Dispatchers.IO ) {
        repository.update(note)
    }
}