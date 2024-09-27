package ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import resources.MyStrings
import logic.getListOfWords
import logic.parseLettersCount
import kotlinx.coroutines.launch

const val NOT_A_NUMBER = -1
const val TOO_SHORT_OR_LONG = 0

@Composable
fun ScreenWordLengthV2() {
    var wordLengthString by remember { mutableStateOf("") }
    var wordList by remember { mutableStateOf<List<String>>(emptyList()) }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val onWordLengthButtonClick: () -> Unit = {
        when (val length = parseLettersCount(wordLengthString)) {
            NOT_A_NUMBER -> {
                // Отображение ошибки
                coroutineScope.launch { // Запускаем корутину
                    showErrorMessage(snackbarHostState, MyStrings.NOT_A_NUMBER)
                }
            }

            TOO_SHORT_OR_LONG -> {
                coroutineScope.launch { // Запускаем корутину
                    showErrorMessage(snackbarHostState, MyStrings.TOO_SHORT_OR_LONG)
                }
            }

            else -> {
                wordList = getListOfWords(length)
            }
        }
    }

    SnackbarHost(
        hostState = snackbarHostState
    )
    ScreenWordLengthDisplayV2(
        value = wordLengthString,
        onValueChange = { wordLengthString = it },
        onButtonClick = onWordLengthButtonClick
    )
}

@Composable
private fun ScreenWordLengthDisplayV2(
    value: String,
    onValueChange: (String) -> Unit,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = MyStrings.PARSE_LENGTH,
            modifier = Modifier
                .padding(bottom = 24.dp),
            fontSize = 36.sp
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(MyStrings.LABEL_PARSE_LENGTH) },
            modifier = Modifier
                .padding(bottom = 24.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Button(onClick = onButtonClick) {
            Text(text = MyStrings.NEXT)
        }
    }
}