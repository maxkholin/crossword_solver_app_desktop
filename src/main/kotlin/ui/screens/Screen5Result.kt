package ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import logic.getListOfWords
import resources.MyStrings

@Composable
fun Screen5Result() {
//    val words = getListOfWords(5)
    val words = mutableListOf<String>()

    if (words.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = MyStrings.NOT_FOUND,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {}) {
                Text(
                    text = MyStrings.TRY_AGAIN
                )
            }
        }
    } else {
        Text(
            text = ""
        )
        LazyColumn {
            items(words) { word ->
                Text(
                    text = word,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }


}