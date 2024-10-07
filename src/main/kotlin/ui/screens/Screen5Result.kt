package ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.LetterButtonModel
import logic.searchBySet
import resources.MyStrings
import ui.MODE_SET

@Composable
fun Screen5Result(
    wordLength: Int,
    words: List<String>,
    mode: Int,
    chosenLetters: Set<LetterButtonModel>,
    exludeLetters: Set<LetterButtonModel>
) {
    val foundWords = if (mode == MODE_SET) {
        searchBySet(words, chosenLetters, exludeLetters)
    } else {
        listOf("Привет", " Ромашки")
    }

    if (foundWords.isEmpty()) {
        TryAgain(wordLength, chosenLetters, exludeLetters)
    } else {
        Result(foundWords)
    }
}


@Composable
private fun Result(words: List<String>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = MyStrings.RESULT + words.size,
            fontSize = 48.sp,
            modifier = Modifier.padding(horizontal = 100.dp, vertical = 32.dp)
        )
        LazyColumn {
            items(words) { word ->
                Text(
                    text = word,
                    fontSize = 36.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
private fun TryAgain(
    wordLength: Int,
    chosenLetters: Set<LetterButtonModel>,
    exludeLetters: Set<LetterButtonModel>
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = MyStrings.NOT_FOUND,
            fontSize = 48.sp
        )
        Spacer(modifier = Modifier.height(48.dp))
        Button(onClick = {
//            App()
        }) {
            Text(
                text = MyStrings.TRY_AGAIN,
                fontSize = 36.sp
            )
        }
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "length = $wordLength, " +
                    "choose letters = ${chosenLetters.joinToString(" ")}" +
                    "exlude letters = ${exludeLetters.joinToString(" ")}",
            fontSize = 24.sp
        )
    }
}