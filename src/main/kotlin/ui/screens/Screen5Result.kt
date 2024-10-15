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
    words: List<String>,
    mode: Int,
    chosenLetters: Set<LetterButtonModel>,
    exludeLetters: Set<LetterButtonModel>,
    onTryAgainButtonClick: () -> Unit
) {
    val foundWords = if (mode == MODE_SET) {
        searchBySet(words, chosenLetters, exludeLetters)
    } else {
        listOf("Привет", " Ромашки")
    }

    if (foundWords.isEmpty()) {
        TryAgain(onTryAgainButtonClick)
    } else {
        Result(foundWords, onTryAgainButtonClick)
    }
}

@Composable
private fun Result(
    words: List<String>,
    onTryAgainButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = MyStrings.RESULT + words.size,
            fontSize = 48.sp,
            modifier = Modifier.padding(horizontal = 100.dp, vertical = 32.dp)
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 16.dp)
        ) {
            items(words) { word ->
                Text(
                    text = word,
                    fontSize = 36.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        TryAgainButton(onTryAgainButtonClick)
    }
}

@Composable
private fun TryAgain(
    onTryAgainButtonClick: () -> Unit
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
        TryAgainButton(onTryAgainButtonClick)
        Spacer(modifier = Modifier.height(48.dp))
    }
}

@Composable
fun TryAgainButton(onTryAgainButtonClick: () -> Unit) {
    Button(onClick = onTryAgainButtonClick,
        modifier = Modifier.padding(16.dp)) {
        Text(
            text = MyStrings.TRY_AGAIN,
            fontSize = 36.sp
        )
    }
}
