package com.example.jetnoteapp.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@Composable
fun InputText(
    modifier: Modifier = Modifier,
    text : String,
    label : String,
    onTextChange : (String) -> Unit,
    maxLines : Int = 1,
    singleLine : Boolean =  false,
    onImeAction: () -> Unit = {}



){

   val keyboardController = LocalSoftwareKeyboardController.current

  TextField(value = text,
      onValueChange = onTextChange,
      modifier = modifier,
      maxLines = maxLines,
      label = { Text(text = label) },
      keyboardOptions = KeyboardOptions(imeAction =ImeAction.Done),
      keyboardActions = KeyboardActions(onDone = {
          onImeAction()
          if (keyboardController != null) {
              keyboardController.hide()
          }
      }  )

  )



}
