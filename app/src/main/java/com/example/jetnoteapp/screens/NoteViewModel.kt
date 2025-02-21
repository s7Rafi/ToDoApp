package com.example.jetnoteapp.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.jetnoteapp.model.Note

class NoteViewModel : ViewModel() {

    var noteList = mutableStateListOf<Note>()

    fun addNotes(note: Note){
        noteList.add(note)
    }
    fun removeNotes(note: Note){
        noteList.remove(note)
    }
    fun getAllNotes() : List<Note>{
        return noteList
    }


}