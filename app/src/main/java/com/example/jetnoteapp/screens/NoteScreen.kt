package com.example.jetnoteapp.screens

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnoteapp.components.InputText
import com.example.jetnoteapp.model.Note
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNotes: (Note) -> Unit,
    onRemoveNotes: (Note) -> Unit){
   Column(modifier = Modifier.fillMaxWidth().padding(4.dp)){

            val title = remember { mutableStateOf("") }
            val description = remember { mutableStateOf("") }

          val context = LocalContext.current

            TopAppBar(
                title = {
                    Text("JetNoteApp")
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = ""
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xC6DADFEE))
            )


        Spacer(modifier = Modifier.height(15.dp))
         Column(modifier = Modifier.fillMaxWidth().padding(12.dp),
             horizontalAlignment = Alignment.CenterHorizontally) {

             InputText(text = title.value,
                 label = "Title",
                 onTextChange = {
                     if (it.all{ char ->
                         char.isLetter() || char.isWhitespace()
                     }) title.value = it
                 }, maxLines = 1)


             InputText(text = description.value,
                 label = "Description",
                 onTextChange = {
                     if (it.all { char ->
                         char.isLetter() || char.isWhitespace()
                     }) description.value = it
                 }, maxLines = 1)

             NoteButton(text = "Save",
                 onclick = {
                     if (title.value.isNotBlank() && description.value.isNotBlank()){
                         // save
                         onAddNotes(Note(title = title.value, description = description.value))
                         title.value = ""
                         description.value = ""
                         Toast.makeText(context, "Text Added",Toast.LENGTH_SHORT).show()
                     }
                 }, modifier = Modifier)


             Divider(modifier = Modifier.padding(10.dp))

             LazyColumn {
                 items(notes){ Note ->

                     RowTable(note = Note, onNoteClicked = {
                           onRemoveNotes(Note)

                     })

                 }
             }



         }

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RowTable(modifier: Modifier = Modifier,
             note : Note,
             onNoteClicked : () -> Unit){


            Surface(modifier = modifier.fillMaxWidth().padding(4.dp)
                .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp)),
                color = Color.Magenta, shadowElevation = 5.dp
                ) {
                Column (modifier = modifier
                    .padding(horizontal = 14.dp, vertical = 8.dp).clickable { onNoteClicked() },
                    horizontalAlignment = Alignment.CenterHorizontally
                ,){
                    Text(note.title, color = MaterialTheme.colorScheme.primary)
                    Text(note.description, color = MaterialTheme.colorScheme.primary)
                    Text(note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d, MMM") ), )





                }

            }

}

@Composable
fun NoteButton(modifier: Modifier,
               text : String,
               onclick : () -> Unit,
               enabled : Boolean = true
               ){

    Button(onClick = onclick,
        modifier = modifier,
        shape = CircleShape,
        enabled = enabled
        ) {

        Text(text)
    }

}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
//    NoteScreen(notes = , onAddNotes = {}, onRemoveNotes = {})
}