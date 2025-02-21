package com.example.jetnoteapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetnoteapp.screens.NoteScreen
import com.example.jetnoteapp.screens.NoteViewModel
import com.example.jetnoteapp.ui.theme.JetNoteAppTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetNoteAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyApp(noteViewModel: NoteViewModel = viewModel()) {
     val noteList = noteViewModel.getAllNotes()
   NoteScreen(
       notes = noteList,
       onAddNotes = {
          noteViewModel.addNotes(it)

       },
       onRemoveNotes = {

          noteViewModel.removeNotes(it)
       })
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetNoteAppTheme {
       MyApp()
    }
}