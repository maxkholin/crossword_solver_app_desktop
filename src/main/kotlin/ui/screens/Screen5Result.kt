package ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.LetterButtonModel
import logic.searchByMask
import logic.searchBySet
import resources.MyStrings
import ui.MODE_SET

@Composable
fun ScreenResult(
    words: List<String>,
    mode: Int,
    countOfLetters: Int,
    chosenLetters: Set<LetterButtonModel>,
    exludeLetters: Set<LetterButtonModel>,
    maskWord: String,
    onTryAgainButtonClick: () -> Unit
) {
    val foundWords = if (mode == MODE_SET) {
        searchBySet(words, chosenLetters, exludeLetters)
    } else {
        searchByMask(words, maskWord)
    }

    if (foundWords.isEmpty()) {
        TryAgain(onTryAgainButtonClick)
    } else {
        Result(foundWords, countOfLetters, onTryAgainButtonClick)
    }
}

@Composable
private fun Result(
    words: List<String>,
    countOfLetters: Int,
    onTryAgainButtonClick: () -> Unit
) {
    val gridColumns = if (countOfLetters <= 7) {
        3
    } else {
        2
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = MyStrings.RESULT + words.size,
            fontSize = 48.sp,
            modifier = Modifier.padding(horizontal = 100.dp, vertical = 32.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(gridColumns),
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
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
    Button(
        onClick = onTryAgainButtonClick,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = MyStrings.TRY_AGAIN,
            fontSize = 36.sp
        )
    }
}
