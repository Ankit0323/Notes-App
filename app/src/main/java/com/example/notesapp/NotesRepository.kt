package com.example.notesapp

import androidx.lifecycle.LiveData

class NotesRepository(private val noteDao : NotesDao) {

    var getAllNotes :LiveData<List<Notes>> = noteDao.getAll()

    suspend fun insert(note :Notes){
        noteDao.insert(note)
    }

    suspend fun delete(note:Notes){
        noteDao.delete(note)
    }
    suspend fun update(note:Notes){
        noteDao.update(note)
    }
}