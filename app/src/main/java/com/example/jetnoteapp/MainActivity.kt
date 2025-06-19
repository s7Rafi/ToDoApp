package com.example.jetnoteapp

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetnoteapp.model.Note
import com.example.jetnoteapp.screens.NoteScreen
import com.example.jetnoteapp.screens.NoteViewModel
import com.example.jetnoteapp.ui.theme.JetNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetNoteAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val noteViewModel = viewModel<NoteViewModel>()
                    MyApp(noteViewModel)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyApp(noteViewModel: NoteViewModel) {
     val noteList = noteViewModel.noteList.collectAsState().value
   NoteScreen(
       notes = noteList,
       onAddNotes = {
          noteViewModel.addNote(it)

       },
       onRemoveNotes = {
           noteViewModel.removeNote(it)
       } ,

       onUpdateNote = {
           noteViewModel.updateNote(it)
       }


       )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetNoteAppTheme {
      // MyApp(noteViewModel )
    }
}