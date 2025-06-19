package com.example.jetnoteapp.screens

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnoteapp.Repository.NoteRepository
import com.example.jetnoteapp.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    //var noteList = mutableStateListOf<Note>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes()
                .distinctUntilChanged()
                .collect {listOfNotes ->
                    _noteList.value = listOfNotes
                    if (listOfNotes.isEmpty()) {
                        Log.d("Empty", ": Empty list")
                    }

                }
        }
    }




     fun addNote(note: Note) =   viewModelScope.launch { repository.addNote(note) }
     fun removeNote(note: Note) = viewModelScope.launch { repository.removeNote(note) }
     fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }


}