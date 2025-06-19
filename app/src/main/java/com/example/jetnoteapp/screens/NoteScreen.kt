package com.example.jetnoteapp.screens

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnoteapp.components.InputText
import com.example.jetnoteapp.model.Note
import com.example.jetnoteapp.utils.formatDate

@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNotes: (Note) -> Unit,
    onRemoveNotes: (Note) -> Unit,
    onUpdateNote: (Note) -> Unit)
   {

       val title = remember { mutableStateOf("") }
       val description = remember { mutableStateOf("") }

       val isEditing = remember { mutableStateOf(false) }
       val editingNote = remember { mutableStateOf<Note?>(null) }


       val context = LocalContext.current


   Column(modifier = Modifier
       .fillMaxWidth()
       .padding(4.dp)){



            TopAppBar(
                title = {
                    Text("ToDoApp")
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Icon"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFDADFE3))
            )


        Spacer(modifier = Modifier.height(15.dp))
         Column(modifier = Modifier
             .fillMaxWidth()
             .padding(12.dp),
             horizontalAlignment = Alignment.CenterHorizontally) {

             InputText(text = title.value,
                 label = "Title",
                 onTextChange = {
                     if (it.all { char ->
                             char.isLetter() || char.isWhitespace()
                         }) title.value = it
                 }, maxLines = 1
             )


             InputText(text = description.value,
                 label = "Task",
                 onTextChange = {
                     if (it.all { char ->
                             char.isLetter() || char.isWhitespace()
                         }) description.value = it
                 }, maxLines = 4,  singleLine = false
             )



             NoteButton(text = if(isEditing.value) "Update" else "Save",
                 onclick = {
                     if (title.value.isNotBlank() && description.value.isNotBlank()) {
                         if (isEditing.value && editingNote.value !=null) {
                             val updatedNote = editingNote.value!!.copy(
                                 title = title.value, description = description.value
                             )

                             onUpdateNote(updatedNote)
                             Toast.makeText(context, "Note Updated", Toast.LENGTH_SHORT).show()
                         }else {
                             onAddNotes(Note(title = title.value, description = description.value))

                             Toast.makeText(context, "Task Added", Toast.LENGTH_SHORT).show()
                         }

                         title.value = ""
                         description.value = ""
                         isEditing.value = false
                         editingNote.value = null

                     }
                 }, modifier = Modifier
             )
         }

             Divider(modifier = Modifier.padding(10.dp))

             LazyColumn {
                 items(notes){ Note ->

                     RowTable(note = Note, onNoteClicked = {
                           onRemoveNotes(it)
                         Toast.makeText(context, "Task Removed", Toast.LENGTH_SHORT).show() },
                         onEditedNote ={
                             title.value = it.title
                             description.value = it.description
                             isEditing.value = true
                             editingNote.value = it
                         }
                         )

                 }
             }





    }
}

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RowTable(modifier: Modifier = Modifier,
             note : Note,
             onNoteClicked : (Note) -> Unit,
             onEditedNote : (Note) -> Unit) {


    val isTaskCompleted = remember { mutableStateOf(false) }


    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp)),
        color = Color(0xFFDFE6EB), shadowElevation = 5.dp
    ) {
        Column(
            modifier = modifier
                .padding(vertical = 8.dp)
                .clickable { onNoteClicked(note) },
            horizontalAlignment = Alignment.Start
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = note.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = note.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = formatDate(note.entryDate.time),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }



                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(onClick = {
                        onNoteClicked(note)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Note",
                            tint = Color.Red
                        )

                    }


                    IconButton(onClick = {
                        onEditedNote(note)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Note",
                            tint = Color(0xFF2196F3)
                        )
                    }


                    IconButton(onClick = {
                        isTaskCompleted.value = !isTaskCompleted.value
                    }) {
                        Icon(
                            imageVector = if (isTaskCompleted.value) {
                                Toast.makeText(LocalContext.current, "Task completed", Toast.LENGTH_SHORT).show()
                                Icons.Default.Done
                            }else Icons.Default.Check,
                            contentDescription = "Toggle Task Completion",
                            tint = if (isTaskCompleted.value) Color(0xFF4CAF50) else Color.Gray
                        )
                    }


                }


            }

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