package com.example.jetnoteapp.Repository

import com.example.jetnoteapp.data.NoteDatabaseDao
import com.example.jetnoteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {

    suspend fun addNote(note: Note) = noteDatabaseDao.addNote(note)
    suspend fun updateNote(note: Note) = noteDatabaseDao.updateNote(note)
    suspend fun removeNote(note: Note) = noteDatabaseDao.deleteNote(note)
    suspend fun deleteNoteById(note: Note) = noteDatabaseDao.deleteNote(note)
     fun getAllNotes() : Flow<List<Note>> = noteDatabaseDao.getNotes()
.flowOn(Dispatchers.IO).conflate()
}