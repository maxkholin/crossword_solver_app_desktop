package ui

import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import data.LetterButtonModel
import data.createLetters
import logic.getListOfWords
import resources.MyStrings
import ui.screens.*

const val MODE_SET = 1
const val MODE_MASK = 2

const val SCREEN_LENGTH = 1
const val SCREEN_MODE = 2
const val SCREEN_SET_MODE = 3
const val SCREEN_MASK_MODE = 33
const val SCREEN_EXLUDE_MODE = 4
const val SCREEN_RESULT = 5


@Composable
fun App() {
    var screen by remember { mutableStateOf(1) }
    var countOfLetters by remember { mutableStateOf(0) }
    var words by remember { mutableStateOf<List<String>>(emptyList()) }
    var mode by remember { mutableStateOf(1) }

    val chosenLetters by remember { mutableStateOf(createLetters().toSet()) }
    val exludeLetters by remember { mutableStateOf(createLetters().toSet()) }
    var maskWord by remember { mutableStateOf("") }

    var trigger by remember { mutableStateOf(false) }

    val onLengthButtonClick: (Int) -> Unit = { length ->
        countOfLetters = length
        words = getListOfWords(countOfLetters)
        screen = SCREEN_MODE
    }

    val onModeButtonClick: (Int) -> Unit = { modeButton ->
        if (modeButton == MODE_SET) {
            mode = MODE_SET
            screen = SCREEN_SET_MODE
        } else {
            mode = MODE_MASK
            screen = SCREEN_MASK_MODE
        }
    }

    val onMaskButtonClick: (String) -> Unit = { word ->
        maskWord = word
        screen = SCREEN_RESULT
    }

    val onLetterButtonClick: (LetterButtonModel) -> Unit = { letterButtonModel ->
        letterButtonModel.isPressed = !letterButtonModel.isPressed
        trigger = !trigger
    }

    val onButtonNextClick: () -> Unit = {
        screen = if (screen == SCREEN_SET_MODE) {
            SCREEN_EXLUDE_MODE
        } else {
            SCREEN_RESULT
        }
    }

    val onTryAgainButtonClick: () -> Unit = {
        chosenLetters.forEach { it.isPressed = false }
        exludeLetters.forEach { it.isPressed = false }
        maskWord = ""
        screen = SCREEN_LENGTH
    }

    when (screen) {
        SCREEN_LENGTH -> ScreenWordLength(onLengthButtonClick)
        SCREEN_MODE -> ScreenChooseMode(onModeButtonClick)
        SCREEN_SET_MODE -> ScreenSetMode(onLetterButtonClick, chosenLetters, trigger, onButtonNextClick, screen)
        SCREEN_EXLUDE_MODE -> ScreenSetMode(onLetterButtonClick, exludeLetters, trigger, onButtonNextClick, screen)
        SCREEN_MASK_MODE -> ScreenMaskMode(countOfLetters, onMaskButtonClick)
        SCREEN_RESULT -> {
            val chLetters = chosenLetters.filter { it.isPressed }.toSet()
            val exLetters = exludeLetters.filter { it.isPressed }.toSet()
            ScreenResult(words, mode, countOfLetters, chLetters, exLetters, maskWord, onTryAgainButtonClick)
        }
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = MyStrings.APP_TITLE,
        state = rememberWindowState(width = 800.dp, height = 600.dp),
        resizable = false
    ) {
        App()
    }
}