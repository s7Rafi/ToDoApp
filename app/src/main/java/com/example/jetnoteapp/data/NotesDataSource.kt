package com.example.jetnoteapp.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.jetnoteapp.model.Note

class NotesDataSource {

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadNotes(): List<Note>{
        return listOf(

            Note(title = "Good Morning", description = "Hello there!"),
            Note(title = "Good Morning", description = "Hello there!"),
            Note(title = "Good Morning", description = "Hello there!"),
            Note(title = "Good Morning", description = "Hello there!"),
            Note(title = "Good Morning", description = "Hello there!"),
            Note(title = "Good Morning", description = "Hello there!"),
            Note(title = "Good Morning", description = "Hello there!"),
            Note(title = "Good Morning", description = "Hello there!")



        )
    }
}